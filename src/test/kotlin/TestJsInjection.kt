import com.isyscore.kotlin.common.HttpMethod
import com.isyscore.kotlin.common.http
import com.isyscore.kotlin.common.json.JSONObject
import com.isyscore.kotlin.common.toJson
import com.isyscore.kotlin.swing.UI
import com.isyscore.kotlin.swing.UIStyle
import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.runOnMainThread
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.handler.*
import org.cef.misc.BoolRef
import org.cef.network.CefCookie
import org.cef.network.CefCookieManager
import org.junit.Test
import sun.util.resources.de.CalendarData_de
import java.awt.BorderLayout
import java.io.File
import java.util.Calendar
import java.util.Date
import javax.swing.JFrame
import kotlin.concurrent.thread
import kotlin.system.exitProcess

data class LoginReqData(
    val loginName: String,
    val password: String
)

class TestJsInjection {

    /**
     * 异步登录接口，回调在子线程
     */
    fun loginAsync(host: String, port: Int, userName: String, password: String, callback:(Map<String, Any>) -> Unit) {
        thread {
            val m = mutableMapOf<String, Any>()
            val ret = http {
                url = "http://$host:$port/api/permission/auth/login"
                method = HttpMethod.POST
                data = LoginReqData(userName, password).toJson()
            }
            if (ret == null) {
                callback(m)
                return@thread
            }
            val json = JSONObject(ret)
            if (json.getInt("code") == 0) {
                val jsonData = json.getJSONObject("data")
                val token = jsonData.getString("token")
                val c = Calendar.getInstance()
                c.add(Calendar.DATE, 7)
                m["ISC_SSO_TOKEN"] = token
                m["ISC_SSO_TOKEN_OUTDATE"] = c.timeInMillis
                m["ISC_SSO_USER"] = jsonData.toString()
            }
            callback(m)
        }
    }

    fun CefBrowser.injectLoginInfo(baseUrl: String, info: Map<String, Any>, redirectUrl: String = "") {
        // 写入 storage
        val JSCODE = "window.localStorage.setItem('ISC_SSO_TOKEN_OUTDATE', ${info["ISC_SSO_TOKEN_OUTDATE"]})\n" +
                "window.localStorage.setItem('ISC_SSO_TOKEN', '${info["ISC_SSO_TOKEN"]}')\n" +
                "window.sessionStorage.setItem('ISC_SSO_USER', '${info["ISC_SSO_USER"]}')\n"
        executeJavaScript(JSCODE, "isyscore/storage/_inject_.js", 0)
        // 写入 cookie
        val domain = baseUrl.toHttpUrl().host
        val cookie = CefCookie("X-Isyscore-Permission-Sid", info["ISC_SSO_TOKEN"] as String, domain, "/",
            false, true, null, null, true, Date(info["ISC_SSO_TOKEN_OUTDATE"] as Long)
        )
        val mgr = CefCookieManager.getGlobalManager()
        val ret = mgr.setCookie(baseUrl, cookie)
        if (ret && redirectUrl != "") {
            runOnMainThread {
                loadURL(redirectUrl)
            }
        }
    }

    @Test
    fun test() {
        UI.lookAndFeel(UIStyle.Light)
        val urlIndex = File("./index.html").toURI().toString()
        val frame = rootFrame("CEF") {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            contentBorderPanel {
                val br = browser {
                    config {
                        settings.windowless_rendering_enabled = false
                        // url = "file:/Users/rarnu/Code/github/common-swing/src/test/kotlin/index.html"
                        url = "http://10.30.30.78:38080/"
                        addExitListener {
                            exitProcess(0)
                        }
                        addLoadHandler(object: CefLoadHandlerAdapter() {
                            override fun onLoadEnd(browser: CefBrowser, frame: CefFrame?, httpStatusCode: Int) {
                                if (httpStatusCode == 200) {
                                    val code = """
                                        function focus_element_input_selall() {
    let ele = document.activeElement;
    if (ele.tagName.toUpperCase() === "INPUT") {
        ele.select();
    }
}
"""
                                    browser.executeJavaScript(code, "isyscore/keyboard/inject.js", 0)
                                }
                                super.onLoadEnd(browser, frame, httpStatusCode)
                            }

                        })
                        addKeyboardHandler(object:CefKeyboardHandlerAdapter() {
                            override fun onKeyEvent(browser: CefBrowser, event: CefKeyboardHandler.CefKeyEvent): Boolean {
                                if (event.type == CefKeyboardHandler.CefKeyEvent.EventType.KEYEVENT_KEYUP) {
                                    if ((event.modifiers == 4 || event.modifiers == 128) && event.windows_key_code == 65) {
                                        // browser.executeJavaScript("fillTest('rarnu')", "isyscore/virtual/inject.js", 0)
                                        println("event: $event")
                                        browser.executeJavaScript("focus_element_input_selall()", "isyscore/keyboard/inject.js", 100)
                                        return true
                                    }
                                }
                                return super.onKeyEvent(browser, event)
                            }
                        })
                    }
                }
                horzPanel(position = BorderLayout.SOUTH) {
                    button("Native Call JS") {
                        addActionListener {
                            val jsCall = "fillTest('rarnu')"
                            br.executeJavaScriptASync(jsCall) { ret, isTimeout ->
                                println(ret)
                                if (isTimeout) {
                                    runOnMainThread {
                                        br.browser.reload()
                                    }
                                }
                            }
                        }
                    }
                    button("Open DevTools") {
                        addActionListener {
                            rootDialog(this@rootFrame, title = "DevTools") {
                                contentBorderPanel {
                                    add(br.browser.devTools.uiComponent)
                                }
                                size = 800 x 600
                                isVisible = true
                            }
                        }
                    }
                    button("Set Cookies") {
                        addActionListener {
                            loginAsync("10.30.30.78", 38080, "admin", "1Sysc0re!") {
                                br.browser.injectLoginInfo("http://10.30.30.78:38080/", it, redirectUrl = "http://10.30.30.78:38080/desktop/")
                            }
                        }
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

}
