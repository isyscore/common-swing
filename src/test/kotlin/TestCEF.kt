import com.isyscore.kotlin.swing.UI
import com.isyscore.kotlin.swing.UIStyle
import com.isyscore.kotlin.swing.dsl.*
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.browser.CefMessageRouter
import org.cef.callback.CefQueryCallback
import org.cef.handler.CefKeyboardHandlerAdapter
import org.cef.handler.CefMessageRouterHandlerAdapter
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.JFrame
import kotlin.system.exitProcess


class TestCEF {

    @Test
    fun test() {
        val frame = JFrame("CEF").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        }

        val builder = CefAppBuilder().apply {
            cefSettings.windowless_rendering_enabled = false
            setAppHandler(object : MavenCefAppHandlerAdapter() {
                override fun stateHasChanged(state: CefApp.CefAppState?) {
                    if (state == CefApp.CefAppState.TERMINATED) {
                        exitProcess(0)
                    }
                }
            })
            // addJcefArgs()
        }
        val cefApp = builder.build()
        val client = cefApp.createClient()
        val msgRouter = CefMessageRouter.create()
        client.addMessageRouter(msgRouter)
        val browser = client.createBrowser("https://www.bilibili.com", false, false)
        val bUI = browser.uiComponent

        frame.contentPane = rootBorderPanel {
            add(bUI)
        }
        frame.size { 1024 x 768 }
        // frame.setSize(500, 500)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (_: Exception) {
            }
        }
    }

    class JavaScriptResponseHandle(private var responseHandle: String) {
        var response: String? = null

        override fun toString(): String = "responseHandle $responseHandle"
        fun waitForCall(maximumMsWait: Long) {
            val msStart = System.currentTimeMillis()
            while (response == null) {
                Thread.sleep(100)
                if (System.currentTimeMillis() > (maximumMsWait + msStart)) {
                    println("Timeout, response not given!")
                    break
                }
            }
        }

        fun stopWaiting(response: String?) {
            this.response = response
        }
    }

    class JavascriptResponseWaiter: CefMessageRouterHandlerAdapter() {
        companion object {
            private const val CALL_TEMPLATE = "var returnValue = %1\$s ; cefCallback(\"JavaScriptResponseWaiter,%2\$s,\" + returnValue);"
            private val CURRENT_HANDLE = AtomicInteger()

        }
        private val handles = mutableMapOf<String, JavaScriptResponseHandle>()

        fun executeAndWaitForCallback(browser: CefBrowser, jsCall: String, maximumMsWait: Long): String? {
            val uniqueResponseHandle = getUniqueResponseHandle()
            val handle = JavaScriptResponseHandle(uniqueResponseHandle)
            synchronized(handles) {
                if (handles.containsKey(uniqueResponseHandle)) {
                    throw RuntimeException("Duplicate response handle: $uniqueResponseHandle")
                }
                handles.put(uniqueResponseHandle, handle)
            }
            val call = CALL_TEMPLATE.format(jsCall, uniqueResponseHandle)
            browser.executeJavaScript(call, "JavaScriptResponseWaiter.java/executeAndWaitForCallback", 55)
            handle.waitForCall(maximumMsWait)
            return handle.response
        }

        override fun onQuery(
            browser: CefBrowser,
            frame: CefFrame?,
            queryId: Long,
            request: String,
            persistent: Boolean,
            callback: CefQueryCallback?
        ): Boolean {
            if (request.startsWith("JavaScriptResponseWaiter")) {
                val handleAndResponse = request.substring("JavaScriptResponseWaiter".length + 1)
                val indexOf = handleAndResponse.indexOf(",")
                val handle = handleAndResponse.substring(0, indexOf)
                val response = handleAndResponse.substring(indexOf + 1)
                println("Got response $request; Response for handle $handle; response = $response")
                synchronized(handles) {
                    val javaScriptResponseHandle = handles[handle]
                    javaScriptResponseHandle?.stopWaiting(response)
                }
            }
            return super.onQuery(browser, frame, queryId, request, persistent, callback)
        }

        private fun getUniqueResponseHandle(): String = CURRENT_HANDLE.incrementAndGet().toString()
    }

    @Test
    fun testCefDSL() {
        UI.lookAndFeel(UIStyle.Light)

        val frame = rootFrame("CEF") {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            contentBorderPanel {
                browser {
                    config {
                        settings.windowless_rendering_enabled = false
                        url = "http://10.30.30.78:38080" // "https://www.baidu.com" // "http://10.30.30.78:38080" //"https://html5test.com"
                        addExitListener {
                            exitProcess(0)
                        }
                        addKeyboardHandler(object: CefKeyboardHandlerAdapter() {})
                        addMessageRouterHandler(JavascriptResponseWaiter(), true)
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }

        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (_: Exception) {
            }
        }

    }

}