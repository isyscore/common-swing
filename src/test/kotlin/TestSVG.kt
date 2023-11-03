import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.exception.ResourceNotFoundException
import org.junit.Test
import java.awt.BorderLayout
import javax.swing.ImageIcon

class TestSVG {

    @Test
    fun test() {
        val img = this::class.java.getResource("/logo.svg")?.toURI() ?: throw ResourceNotFoundException("not found")

        val frame = rootFrame("Sample") {
            contentBorderPanel {
                svg(uri = img, position = BorderLayout.CENTER) {}
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


    @Test
    fun testPNG() {
        val img = ImageIcon(this::class.java.getResource("/sample.png"))
        val frame = rootFrame("Sample") {
            contentBorderPanel {
                image(img = img.image) {}
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