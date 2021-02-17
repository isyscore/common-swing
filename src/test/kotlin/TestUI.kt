import com.isyscore.kotlin.swing.dsl.*
import org.junit.Test
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.plaf.metal.MetalLookAndFeel

class TestUI {

    @Test
    fun test() {

        UIManager.setLookAndFeel(MetalLookAndFeel())
        JFrame.setDefaultLookAndFeelDecorated(true)
        JDialog.setDefaultLookAndFeelDecorated(true)
        val frame = JFrame("Test")

        frame.contentPane = rootVertPanel {
            input {  }
            textArea("sample", 10) {  }
            inputPassword {  }
            textPane {  }
            editorPane {  }
            spinner {  }
        }
        frame.setSize(500, 500)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        while (frame.isVisible) {
            try {
                Thread.sleep(10000)
            } catch (e: Exception) {
            }
        }
    }

}