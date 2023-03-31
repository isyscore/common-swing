// import com.bulenkov.darcula.DarculaLaf
import com.isyscore.kotlin.common.join
import com.isyscore.kotlin.swing.UI
import com.isyscore.kotlin.swing.UIStyle
import com.isyscore.kotlin.swing.dsl.*
import com.isyscore.kotlin.swing.runOnMainThread
import org.junit.Test
import java.awt.BorderLayout
import java.awt.BorderLayout.NORTH
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableModel
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.Theme
import java.awt.Color
import javax.swing.border.Border
import kotlin.concurrent.thread

class TestUI {

    @Test
    fun testTabClose() {
        UI.lookAndFeel(UIStyle.Light)
        val vecData = Vector<Vector<Any>>()
        lateinit var t: JTable
        val frame = rootFrame("Table") {
            contentBorderPanel {
                scroller {
                    t = table(model = DefaultTableModel(vecData, Vector(listOf("", "Table", "Edit", "Button")))) {
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
                                t.validate()
                                t.updateUI()
                            }
                        }
                    }
                    button(title = "CLEAN") {
                        addActionListener {
                            vecData.clear()
                            runOnMainThread {
                                t.validate()
                                t.updateUI()
                            }
                        }
                    }
                    button(title = "Show") {
                        addActionListener {
                            val d1 = t.model.getValueAt(0, 1) join t.model.getValueAt(0, 0) join t.model.getValueAt(0, 2)
                            val d2 = t.model.getValueAt(1, 1) join t.model.getValueAt(1, 0) join t.model.getValueAt(1, 2)
                            println("d1 = $d1, d2 = $d2")
                        }
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }

//        frame.contentPane = rootBorderPanel {
//
//            scroller {
//                t = table(model = tm) {
//                    readonlyCell(1)
//                    boolCell(0)
//                    buttonCell(3) { value, row, col ->
//                        println("Clicked => $value, $row, $col")
//                    }
//                    val cb = rootCombobox(array = arrayOf("a", "b", "c")) { }
//                    comboCell(2, cb)
//                    column(0) {
//                        preferredWidth = 20
//                    }
//                    header {
//                        reorderingAllowed = false
//                        resizingAllowed = false
//                    }
//                    autoResizeMode = JTable.AUTO_RESIZE_OFF
//                }
//            }
//
//            horzPanel(position = NORTH) {
//                button(title = "FILL DATA") {
//                    addActionListener {
//                        vecData.clear()
//                        vecData.add(Vector(listOf(false, "Table1", "", "Click")))
//                        vecData.add(Vector(listOf(false, "Table2", "", "Click2")))
//                        runOnMainThread {
//                            t?.validate()
//                            t?.updateUI()
//                        }
//                    }
//                }
//                button(title = "CLEAN") {
//                    // vecData.clear()
//                    addActionListener {
//                        vecData.clear()
//                        runOnMainThread {
//                            t?.validate()
//                            t?.updateUI()
//                        }
//                    }
//                }
//                button(title = "GO GO GO") {
//                    addActionListener {
//                        val d1 = t?.model?.getValueAt(0, 1) join t?.model?.getValueAt(0, 0) join t?.model?.getValueAt(0, 2)
//                        val d2 = t?.model?.getValueAt(1, 1) join t?.model?.getValueAt(1, 0) join t?.model?.getValueAt(1, 2)
//                        println("d1 = $d1, d2 = $d2")
//                    }
//                }
//            }
//
//        }
//        frame.size { 500 x 500 }
//        // frame.setSize(500, 500)
//        frame.setLocationRelativeTo(null)
//        frame.isVisible = true

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
        // frame.isUndecorated = true

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

    @Test
    fun test1() {
        val frame = rootFrame("Sample") {
            contentBorderPanel {
                button("It's a button", position = BorderLayout.SOUTH) {

                }
                scroller {
                    textArea {

                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    @Test
    fun testTab() {
        UI.lookAndFeel(UIStyle.Light)
        val frame = rootFrame("Tab Sample") {
            contentBorderPanel {
                pager(tabPlacement = JTabbedPane.TOP) {
                    borderPanelTab(title = "Welcome", canClose = false) {

                    }
                    borderPanelTab(title = "Tab 1", canClose = true) {

                    }
                    borderPanelTab(title = "Tab 2", canClose = true) {

                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    @Test
    fun testSyntax() {

        val frame = rootFrame("Syntax") {
            contentBorderPanel {
                scroller {
                    custom<RSyntaxTextArea>(CODE) {
                        syntaxEditingStyle = "text/java"
                        Theme.load(javaClass.getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/dark.xml")).apply(this)
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    private val CODE =
"""public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}"""

    @Test
    fun testThis() {
        UI.lookAndFeel(UIStyle.Aero)
        val frame = rootFrame("This!!") {
            contentBorderPanel {
                borderPanel {
                    borderPanel {
                        button("Red") {
                            addActionListener {
                                this@borderPanel.background = Color.red
                            }
                        }
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    @Test
    fun testThread() {
        val frame = rootFrame("Thread") {
            contentBorderPanel {
                button("0") {
                    thread {
                        Thread.sleep(10000)
                        runOnMainThread {
                            this.text = "done"
                        }
                    }
                }
            }
            size = 1024 x 768
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    @Test
    fun testTheme() {
        UI.lookAndFeel(UIStyle.Windows)
        val frame = rootFrame("Theme") {
            contentBorderPanel {
                border = BorderFactory.createEmptyBorder(4,4,4,4)
                split {
                    leftScroller {
                        list(array = arrayOf("Light", "Dark", "Darcula", "Intellij", "Metal", "Motif", "Windows", "WindowsClassic", "GTK")) {
                            border = BorderFactory.createEmptyBorder(4,4,4,4)
                        }
                    }
                    rightBorderPanel {
                        split {
                            leftScroller {
                                minimumSize = 120 x 80
                                tree {  }
                            }
                            rightScroller {
                                textArea("Hello World") {

                                }
                            }
                            dividerLocation = 140
                            dividerSize = 3
                        }
                    }
                    dividerLocation = 140
                    dividerSize = 3
                }

            }
            size = 600 x 400
            setLocationRelativeTo(null)
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    @Test
    fun testCalc() {



        val frame = rootFrame("Calc") {
            contentBorderPanel {
                horzPanel(position = NORTH) {
                    val input1 = input {
                        preferredSize = 100 x 30
                    }
                    label("+") {}
                    val input2 = input {
                        preferredSize = 100 x 30
                    }
                    lateinit var input3: JTextField
                    button("=") {
                        addActionListener {

                        }
                    }
                    input3 = input {
                        preferredSize = 100 x 30
                        isEditable =  false
                    }
                }
            }
            size = 600 x 400
            setLocationRelativeTo(null)
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }

    data class KeyEvt(val keyCode: String, val action: String)

    @Test
    fun testMapReduce() {
        val k1 = KeyEvt("1", "down")
        val k2 = KeyEvt("1", "up")
        val k3 = KeyEvt("0", "down")
        val k4 = KeyEvt("0", "up")
        val k5 = KeyEvt("0", "down")
        val k6 = KeyEvt("0", "up")
        val karr = arrayOf(k1, k2, k3, k4, k5, k6)
        val v = karr.filter { it.action == "up" }.map { it.keyCode }.reduce { acc, c -> acc + c }
        println(v)
    }

    @Test
    fun testFrame() {
        val frame = rootFrame("sample") {
            contentBorderPanel {
                horzPanel(position = NORTH) {
                    button("Click") {

                    }
                }
            }
            size = 600 x 400
            setLocationRelativeTo(null)
            isVisible = true
        }
        while (frame.isVisible) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }
        }
    }


}