import com.formdev.flatlaf.util.SystemInfo
import com.isyscore.kotlin.common.isMac
import com.isyscore.kotlin.swing.dsl.button
import com.isyscore.kotlin.swing.dsl.contentClearPanel
import com.isyscore.kotlin.swing.dsl.rootFrame
import com.isyscore.kotlin.swing.dsl.x
import org.junit.Test
import java.awt.Component
import java.awt.Container
import javax.swing.AbstractButton
import javax.swing.JFrame


class TestTitleBar {

    fun removeMinMaxClose(comp: Component) {
        if (comp is AbstractButton) {
            comp.getParent().remove(comp)
        }
        if (comp is Container) {
            val comps: Array<Component> = comp.components
            var x = 0
            val y = comps.size
            while (x < y) {
                removeMinMaxClose(comps[x])
                x++
            }
        }
    }


    @Test
    fun test() {
        JFrame.setDefaultLookAndFeelDecorated(true)

        val frame = rootFrame("Sample") {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            contentClearPanel {
                button("Click") {
                    preferredSize = 80 x 30
                    setBounds(8, -8, 80, 30)
                }
            }

            size = 800 x 600
            setLocationRelativeTo(null)
            isVisible = true
        }
        while (frame.isVisible) {
            Thread.sleep(1000)
        }
    }

}