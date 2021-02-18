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
//            list(array = arrayOf("a", "b")) {
//                cell<JPanel, String> { value, index, selected, cellHasFocus ->
//                    background = Color.WHITE
//                    if (selected) {
//                        label(title = value) {
//                            size { 100 x 50 }
//                            foreground = Color.RED
//                        }
//                    } else {
//                        label(title = value) {
//                            size { 100 x 50 }
//                            foreground = Color.BLACK
//                        }
//                    }
//                }
//            }
            val tbl = table(arrayRowData = arrayOf(arrayOf("A", true), arrayOf("B", false)), arrayColumnNames = arrayOf("Name", "Checked")) {
                // rowHeight = 50
                cell<JCheckBox> { cell, value, selected, cellHasFocus, row, col ->
                    background = Color.WHITE
                    this.isSelected = value as Boolean
                    addActionListener { cell.setEditValue(this.isSelected, row, col) }
                }
            }
            borderPanel {
                button("Check") {
                    addActionListener {
                        val d1 = tbl.model.getValueAt(0, 1)
                        val d2 = tbl.model.getValueAt(1, 1)
                        println("d1 = $d1, d2 = $d2")
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
        frame.size { 500 x 300 }
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