import com.isyscore.kotlin.swing.UI
import com.isyscore.kotlin.swing.UI.Mac.frameAppearance
import com.isyscore.kotlin.swing.UI.Mac.hookAbout
import com.isyscore.kotlin.swing.UIStyle
import com.isyscore.kotlin.swing.dsl.*
import org.junit.Test

class TestMac {

    @Test
    fun test() {
        UI.Mac.useScreenMenuBar(true)
        UI.Mac.setAppearance(UI.Mac.MacAppearance.SYSTEM)
        UI.Mac.setApplicationName("MyApp")

        UI.lookAndFeel(UIStyle.Dark)

        val frame = rootFrame("MyApp") {
            frameAppearance(fullWindow = true, transparentTitleBar = true)
            contentBorderPanel {

            }
            jMenuBar = rootMenuBar {
                menu("File") {

                }
            }

            hookAbout {
                rootDialog(it, "About") {
                    contentBorderPanel {
                        button("OK") {
                            addActionListener {
                                this@rootDialog.dispose()
                            }
                        }
                    }
                    size = 200 x 100
                    isVisible = true
                }
            }

            size = 600 x 400
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