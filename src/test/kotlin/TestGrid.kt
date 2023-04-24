import com.isyscore.kotlin.swing.component.StretchIcon
import com.isyscore.kotlin.swing.dsl.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Test
import java.awt.BorderLayout
import java.awt.Color
import java.net.URL
import javax.swing.JLabel
import javax.swing.JPanel

class TestGrid {

    class IconPanel(title: String, url: URL): JPanel(BorderLayout()) {
        private val icon = StretchIcon(url)
        init {
            background = Color.WHITE
            label(icon = icon) {
                preferredSize = 108 x 108
            }
            label(title = title, position = BorderLayout.SOUTH, horizontalAlignment = JLabel.CENTER) {
                preferredSize = 108 x 20
            }
        }
    }


    @Test
    fun test() {
        val frame = rootFrame("Grid") {
            contentBorderPanel {
                background = Color.WHITE
                wrapPanel {
                    background = Color.WHITE
                    for (i in 1..40) {
                        custom<IconPanel>(position = "","Item $i",
                            "https://rarnu.xyz/upload/2020/08/workhead-f8b4f22020b44abca50667ce2e170519.jpg".toHttpUrl().toUrl()) {
                        }
                    }
                }
                /*
                scroller(hsbPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
                    border = BorderFactory.createEmptyBorder(16,8,16,0)

                    horzPanel(align = FlowLayout.LEFT, hgap =  8, vgap = 8) {
                        background = Color.WHITE
                        for (i in 1..20) {
                            custom<IconPanel>(position = "","Item $i",
                                "https://rarnu.xyz/upload/2020/08/workhead-f8b4f22020b44abca50667ce2e170519.jpg".toHttpUrl().toUrl()) {
                            }
                        }
                    }
                }

                 */
            }

            size = 600 x 480
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