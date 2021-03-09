import com.bulenkov.darcula.DarculaLaf
import com.isyscore.kotlin.swing.component.toast
import com.isyscore.kotlin.swing.dsl.*
import org.junit.Test
import javax.swing.*

class TestUI {

    @Test
    fun testTabClose() {
        // return c instanceof JButton && "square".equals(((JButton)c).getClientProperty("JButton.buttonType"));
        UIManager.setLookAndFeel(DarculaLaf())
        JFrame.setDefaultLookAndFeelDecorated(true)
        JDialog.setDefaultLookAndFeelDecorated(true)
        val frame = JFrame("Test")
        frame.contentPane = rootClearPanel {
            button("Sample1") {
                bounds { (20 x 20) and (60 x 30) }
            }
            button("Sample1") {
                bounds { (20 x 60) and (60 x 30) }
                this.putClientProperty("JButton.buttonType", "square")

                addActionListener {
                    frame.toast("2333")
                }
            }

            pager {
                bounds { (20 x 100) and (200 x 200) }
                borderPanelTab("2333", canClose = true) {

                }
                borderPanelTab("6666", canClose = true) {

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

        UIManager.setLookAndFeel(DarculaLaf())
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