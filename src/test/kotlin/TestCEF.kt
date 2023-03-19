import com.isyscore.kotlin.swing.UI
import com.isyscore.kotlin.swing.UIStyle
import com.isyscore.kotlin.swing.dsl.*
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.browser.CefBrowser
import org.cef.browser.CefFrame
import org.cef.browser.CefMessageRouter
import org.cef.handler.CefKeyboardHandler
import org.cef.handler.CefKeyboardHandlerAdapter
import org.cef.handler.CefLifeSpanHandlerAdapter
import org.junit.Test
import java.beans.Visibility
import javax.swing.JDialog
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
                    };
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
            } catch (e: Exception) {
            }
        }
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
                        url = "http://10.30.30.78:38080" // ""https://www.baidu.com" // "http://10.30.30.78:38080" //"https://html5test.com"
                        addExitListener {
                            exitProcess(0)
                        }
                        addLifeSpanHandler(object : CefLifeSpanHandlerAdapter() {
                            override fun onBeforePopup(
                                sender: CefBrowser,
                                frame: CefFrame?,
                                targetUrl: String?,
                                targetFrameName: String?
                            ): Boolean {
                                sender.loadURL(targetUrl)
                                return true
                            }
                        })
                        addKeyboardHandler(object: CefKeyboardHandlerAdapter() {})
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