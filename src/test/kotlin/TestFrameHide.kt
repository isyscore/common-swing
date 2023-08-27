import com.isyscore.kotlin.swing.dsl.button
import com.isyscore.kotlin.swing.dsl.contentBorderPanel
import com.isyscore.kotlin.swing.dsl.rootFrame
import com.isyscore.kotlin.swing.dsl.x
import org.junit.Test
import java.awt.BorderLayout
import javax.swing.JFrame

class TestFrameHide {

    @Test
    fun test() {
        var dialog: JFrame? = null

        val frame = rootFrame("Sample") {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            contentBorderPanel {
                button("Show", position = BorderLayout.NORTH) {
                    addActionListener {
                        if (dialog == null) {
                            dialog = rootFrame("Dialog") {
                                defaultCloseOperation = JFrame.HIDE_ON_CLOSE
                                contentBorderPanel {

                                }
                                size = 600 x 480
                                setLocationRelativeTo(null)
                            }
                        }
                        dialog?.isVisible = true
                    }
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