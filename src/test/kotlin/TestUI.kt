import com.isyscore.kotlin.swing.dsl.*
import org.junit.Test
import java.awt.Color
import java.awt.Component
import java.util.*
import javax.swing.*
import javax.swing.event.CellEditorListener
import javax.swing.plaf.metal.MetalLookAndFeel
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer

class TestUI {

    @Test
    fun test() {

        UIManager.setLookAndFeel(MetalLookAndFeel())
        JFrame.setDefaultLookAndFeelDecorated(true)
        JDialog.setDefaultLookAndFeelDecorated(true)
        val frame = JFrame("Test")

        frame.contentPane = rootVertPanel {
//            input {  }
//            textArea("sample", 10) {  }
//            inputPassword {  }
//            textPane {  }
//            editorPane {  }
//            spinner {  }
            list(array = arrayOf("a", "b")) {
                cell<JPanel, String> { value, index, selected, cellHasFocus ->
                    background = Color.WHITE
                    if (selected) {
                        label(title = value) {
                            size { 100 x 50 }
                            foreground = Color.RED
                        }
                    } else {
                        label(title = value) {
                            size { 100 x 50 }
                            foreground = Color.BLACK
                        }
                    }
                }
            }
            table(arrayRowData = arrayOf(arrayOf("A", true), arrayOf("B", false)), arrayColumnNames = arrayOf("Name", "Checked")) {
                // rowHeight = 50
                cell<JButton> { value, isSelected, cellHasFocus, row, col ->
                    this.text = "$value"
                    addActionListener {
                        println(value)
                    }
                }

            }
//            clearPanel {
//                size { 500 x 200 }
//                label(title = "23333") {
//                    bounds { (50 x 50) and (100 x 30) }
//                }
//            }
        }
        frame.size { 500 x 800 }
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

}