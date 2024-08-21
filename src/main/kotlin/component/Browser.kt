package com.isyscore.kotlin.swing.component

import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.IProgressHandler
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.CefClient
import org.cef.CefSettings
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.browser.CefMessageRouter
import org.cef.callback.CefQueryCallback
import org.cef.handler.*
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.JPanel
import kotlin.concurrent.thread

class Browser : JPanel(BorderLayout()) {

    class JavaScriptResponseHandle(private var responseHandle: String) {
        var response: String? = null

        override fun toString(): String = "responseHandle $responseHandle"
        fun waitForCall(maximumMsWait: Long): Boolean {
            val msStart = System.currentTimeMillis()
            var isTimeout = false
            while (response == null) {
                Thread.sleep(50)
                if (System.currentTimeMillis() > (maximumMsWait + msStart)) {
                    isTimeout = true
                    break
                }
            }
            return isTimeout
        }

        fun stopWaiting(response: String?) {
            this.response = response
        }
    }

    class JavascriptResponseWaiter : CefMessageRouterHandlerAdapter() {
        companion object {
            private const val CALL_TEMPLATE = "var returnValue = %1\$s ; cefCallback(\"JavaScriptResponseWaiter,%2\$s,\" + returnValue);"
            private val CURRENT_HANDLE = AtomicInteger()

        }

        private val handles = mutableMapOf<String, JavaScriptResponseHandle>()

        fun executeAndWaitForCallback(browser: CefBrowser, jsCall: String, maximumMsWait: Long): Pair<String?, Boolean> {
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
            val isTimeout = handle.waitForCall(maximumMsWait)
            return handle.response to isTimeout
        }

        override fun onQuery(browser: CefBrowser, frame: CefFrame?, queryId: Long, request: String, persistent: Boolean, callback: CefQueryCallback?): Boolean {
            if (request.startsWith("JavaScriptResponseWaiter")) {
                val handleAndResponse = request.substring("JavaScriptResponseWaiter".length + 1)
                val indexOf = handleAndResponse.indexOf(",")
                val handle = handleAndResponse.substring(0, indexOf)
                val response = handleAndResponse.substring(indexOf + 1)
                synchronized(handles) {
                    val javaScriptResponseHandle = handles[handle]
                    javaScriptResponseHandle?.stopWaiting(response)
                }
            }
            return super.onQuery(browser, frame, queryId, request, persistent, callback)
        }

        private fun getUniqueResponseHandle(): String = CURRENT_HANDLE.incrementAndGet().toString()
    }

    class BrowserConfig {
        internal val builder = CefAppBuilder()
        internal var _onProgress: IProgressHandler? = null
        internal var _onExit: ActionListener? = null
        internal var _onLifeSpan: CefLifeSpanHandler? = null
        internal var _onDialog: CefDialogHandler? = null
        internal var _onDisplay: CefDisplayHandler? = null
        internal var _onContextMenu: CefContextMenuHandler? = null
        internal var _onDownload: CefDownloadHandler? = null
        internal var _onDrag: CefDragHandler? = null
        internal var _onFocus: CefFocusHandler? = null
        internal var _onJSDialog: CefJSDialogHandler? = null
        internal var _onKeyboard: CefKeyboardHandler? = null
        internal var _onLoad: CefLoadHandler? = null
        internal var _onPrint: CefPrintHandler? = null
        internal var _onRequest: CefRequestHandler? = null

        var url = "about:blank"
        var args: Array<String>? = null
        var isTransparent = false
        internal val _handler = mutableMapOf<CefMessageRouterHandler, Boolean>()

        val settings: CefSettings get() = builder.cefSettings

        fun addMessageRouterHandler(h: CefMessageRouterHandler, first: Boolean) {
            _handler[h] = first
        }

        fun removeMessageRouterHandler(h: CefMessageRouterHandler) {
            _handler.remove(h)
        }

        fun addRequestHandler(listener: CefRequestHandler) {
            _onRequest = listener
        }

        fun removeRequestHandler() {
            _onRequest = null
        }

        fun addPrintHandler(listener: CefPrintHandler) {
            _onPrint = listener
        }

        fun removePrintHandler() {
            _onPrint = null
        }

        fun addLoadHandler(listener: CefLoadHandler) {
            _onLoad = listener
        }

        fun removeLoadHandler() {
            _onLoad = null
        }

        fun addKeyboardHandler(listener: CefKeyboardHandler) {
            _onKeyboard = listener
        }

        fun removeKeyboardHandler() {
            _onKeyboard = null
        }

        fun addJsDialogHandler(listener: CefJSDialogHandler) {
            _onJSDialog = listener
        }

        fun removeJsDialogHandler() {
            _onJSDialog = null
        }

        fun addFocusHandler(listener: CefFocusHandler) {
            _onFocus = listener
        }

        fun removeFocusHandler() {
            _onFocus = null
        }

        fun addDragHandler(listener: CefDragHandler) {
            _onDrag = listener
        }

        fun removeDragHandler() {
            _onDrag = null
        }

        fun addDownloadHandler(listener: CefDownloadHandler) {
            _onDownload = listener
        }

        fun removeDownloadHandler() {
            _onDownload = null
        }

        fun addContentMenuHandler(listener: CefContextMenuHandler) {
            _onContextMenu = listener
        }

        fun removeContentMenuHandler() {
            _onContextMenu = null
        }

        fun addDisplayHandler(listener: CefDisplayHandler) {
            _onDisplay = listener
        }

        fun removeDisplayHandler() {
            _onDisplay = null
        }

        fun addDialogHandler(listener: CefDialogHandler) {
            _onDialog = listener
        }

        fun removeDialogHandler() {
            _onDialog = null
        }

        fun addProgressHandler(listener: IProgressHandler) {
            _onProgress = listener
        }

        fun removeProgressHandler() {
            _onProgress = null
        }

        fun addExitListener(listener: ActionListener) {
            _onExit = listener
        }

        fun removeExitListener() {
            _onExit = null
        }

        fun addLifeSpanHandler(listener: CefLifeSpanHandler) {
            _onLifeSpan = listener
        }

        fun removeLifeSpanHandler() {
            _onLifeSpan = null
        }

    }

    private val _cfg = BrowserConfig()

    private lateinit var cefApp: CefApp
    private lateinit var cefClient: CefClient
    private lateinit var cefBrowser: CefBrowser
    private lateinit var cefJsHandler: JavascriptResponseWaiter

    val client: CefClient get() = cefClient
    val app: CefApp get() = cefApp
    val browser: CefBrowser get() = cefBrowser

    fun config(cfg: BrowserConfig.() -> Unit) {
        cfg(_cfg)
    }

    fun render() {
        _cfg.builder.setAppHandler(object : MavenCefAppHandlerAdapter() {
            override fun stateHasChanged(state: CefApp.CefAppState?) {
                if (state == CefApp.CefAppState.TERMINATED) {
                    _cfg._onExit?.actionPerformed(ActionEvent(cefBrowser, 0, ""))
                }
            }
        })
        if (_cfg._onProgress != null) _cfg.builder.setProgressHandler(_cfg._onProgress)
        cefApp = _cfg.builder.build()
        cefClient = cefApp.createClient()

        cefClient.addDialogHandler(_cfg._onDialog)
        cefClient.addDisplayHandler(_cfg._onDisplay)
        cefClient.addContextMenuHandler(_cfg._onContextMenu)
        cefClient.addDownloadHandler(_cfg._onDownload)
        cefClient.addDragHandler(_cfg._onDrag)
        cefClient.addFocusHandler(_cfg._onFocus)
        cefClient.addJSDialogHandler(_cfg._onJSDialog)
        cefClient.addKeyboardHandler(_cfg._onKeyboard)
        cefClient.addLoadHandler(_cfg._onLoad)
        cefClient.addPrintHandler(_cfg._onPrint)
        cefClient.addRequestHandler(_cfg._onRequest)
        cefClient.addLifeSpanHandler(_cfg._onLifeSpan)

        val msgRouter = CefMessageRouter.create()
        cefJsHandler = JavascriptResponseWaiter()
        msgRouter.addHandler(cefJsHandler, true)

        _cfg._handler.forEach { (t, u) ->
            msgRouter.addHandler(t, u)
        }
        cefClient.addMessageRouter(msgRouter)

        cefBrowser = cefClient.createBrowser(_cfg.url, _cfg.builder.cefSettings.windowless_rendering_enabled, _cfg.isTransparent)

        val ui = cefBrowser.uiComponent
        add(ui, BorderLayout.CENTER)
    }

    /**
     * 以同步形式执行js并返回
     * 当被执行的js拥有返回值时，将得到返回值，否则返回null
     * 第二个返回参数为"是否超时"
     */
    fun executeJavaScriptSync(jsCall: String, timeout: Long = 5000): Pair<String?, Boolean> {
        val callbackMethod = "function cefCallback(args) { window.cefQuery({ request : args, onFailure : function(error_code, error_message) { } }); }"
        browser.executeJavaScript(callbackMethod, "isyscore/browser/callback.js", 0)
        return cefJsHandler.executeAndWaitForCallback(browser, jsCall, timeout)
    }

    /**
     * 以异步形式执行js并返回
     * 当被执行的js拥有返回值时，将得到返回值，否则返回null
     * 当第二个返回参数为"是否超时"
     */
    fun executeJavaScriptASync(jsCall: String, timeout: Long = 5000, callback: (String?, Boolean) -> Unit) {
        val callbackMethod = "function cefCallback(args) { window.cefQuery({ request : args, onFailure : function(error_code, error_message) { } }); }"
        browser.executeJavaScript(callbackMethod, "isyscore/browser/callback.js", 0)
        thread {
            val (ret, isTimeout) = cefJsHandler.executeAndWaitForCallback(browser, jsCall, timeout)
            callback(ret, isTimeout)
        }
    }
}

