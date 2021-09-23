// import com.bulenkov.darcula.DarculaLaf
import com.isyscore.kotlin.common.join
import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.runOnMainThread
import org.junit.Test
import java.awt.BorderLayout.NORTH
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableModel

class TestUI {

    @Test
    fun testTabClose() {

        // return c instanceof JButton && "square".equals(((JButton)c).getClientProperty("JButton.buttonType"));
        // UIManager.setLookAndFeel(DarculaLaf())
        JFrame.setDefaultLookAndFeelDecorated(true)
        JDialog.setDefaultLookAndFeelDecorated(true)
        val frame = JFrame("Test")
        var t: JTable? = null
        val vecData = Vector<Vector<Any>>()
        val tm = DefaultTableModel(vecData, Vector(listOf("", "Table", "Edit", "Button")))
        frame.contentPane = rootBorderPanel {
            scroller {
                t = table(model = tm) {
                    readonlyCell(1)
                    boolCell(0)
                    buttonCell(3) { value, row, col ->
                        println("Clicked => $value, $row, $col")
                    }
                    val cb = rootCombobox(array = arrayOf("a", "b", "c")) { }
                    comboCell(2, cb)
                    column(0) {
                        preferredWidth = 20
                    }
                    header {
                        reorderingAllowed = false
                        resizingAllowed = false
                    }
                    autoResizeMode = JTable.AUTO_RESIZE_OFF
                }
            }

            horzPanel(position = NORTH) {
                button(title = "FILL DATA") {
                    addActionListener {
                        vecData.clear()
                        vecData.add(Vector(listOf(false, "Table1", "", "Click")))
                        vecData.add(Vector(listOf(false, "Table2", "", "Click2")))
                        runOnMainThread {
                            t?.validate()
                            t?.updateUI()
                        }
                    }
                }
                button(title = "CLEAN") {
                    // vecData.clear()
                    addActionListener {
                        vecData.clear()
                        runOnMainThread {
                            t?.validate()
                            t?.updateUI()
                        }
                    }
                }
                button(title = "GO GO GO") {
                    addActionListener {
                        val d1 = t?.model?.getValueAt(0, 1) join t?.model?.getValueAt(0, 0) join t?.model?.getValueAt(0, 2)
                        val d2 = t?.model?.getValueAt(1, 1) join t?.model?.getValueAt(1, 0) join t?.model?.getValueAt(1, 2)
                        println("d1 = $d1, d2 = $d2")
                    }
                }
            }

        }
        frame.size { 500 x 500 }
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
    fun test() {

        // UIManager.setLookAndFeel(DarculaLaf())
        // UIManager.setLookAndFeel(MetalLookAndFeel())
         JFrame.setDefaultLookAndFeelDecorated(true)
         JDialog.setDefaultLookAndFeelDecorated(true)
        val frame = JFrame("Test")
        frame.isUndecorated = true

        /*
        f.setUndecorated(true);
    f.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
         */

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

        frame.rootPane.windowDecorationStyle = JRootPane.FRAME

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