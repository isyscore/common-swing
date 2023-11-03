@file:Suppress("unused", "DuplicatedCode")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import com.isyscore.kotlin.swing.inline.newClassInstance
import org.apache.batik.anim.dom.SAXSVGDocumentFactory
import org.apache.batik.swing.JSVGCanvas
import org.apache.batik.util.XMLResourceDescriptor
import java.awt.*
import java.io.File
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.util.*
import javax.swing.*
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

@ContextDsl
fun rootToolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar = JToolBar(orientation).apply(block)

fun JToolBar.panel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.borderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.clearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.vertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.horzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.gridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.wrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    add(pnl)
    return pnl
}

fun JToolBar.pager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    add(pager)
    return pager
}

fun JToolBar.scroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    add(scroll)
    return scroll
}

fun JToolBar.split(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    add(sp)
    return sp
}

fun JToolBar.toolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    add(tb)
    return tb
}

fun JToolBar.layer(block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    add(lay)
    return lay
}

fun JToolBar.label(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel {
    val lbl = JLabel(title, icon, horizontalAlignment).apply(block)
    add(lbl)
    return lbl
}

fun JToolBar.input(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField {
    val txt = KTextField(text, column).apply(block)
    add(txt)
    return txt
}

fun <T> JToolBar.combobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> {
    val cb = when {
        model != null -> JComboBox(model)
        array != null -> JComboBox(array)
        vector != null -> JComboBox(vector)
        else -> JComboBox()
    }.apply(block)
    add(cb)
    return cb
}

fun JToolBar.button(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton {
    val b = JButton(title, icon).apply(block)
    add(b)
    return b
}

fun JToolBar.checkbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox {
    val chk = JCheckBox(title, icon, selected).apply(block)
    add(chk)
    return chk
}

fun JToolBar.radio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton {
    val rd = JRadioButton(title, icon, selected).apply(block)
    add(rd)
    return rd
}

fun JToolBar.toggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton {
    val tg = JToggleButton(title, icon, selected).apply(block)
    add(tg)
    return tg
}

fun JToolBar.textArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea {
    val ta = KTextArea(text, rows, columns).apply(block)
    add(ta)
    return ta
}

fun JToolBar.inputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField {
    val txt = KPasswordField(text, column).apply(block)
    add(txt)
    return txt
}

fun JToolBar.textPane(block: KTextPane.() -> Unit): KTextPane {
    val txt = KTextPane().apply(block)
    add(txt)
    return txt
}

fun JToolBar.editorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane {
    val txt = when {
        url != null -> KEditorPane(url)
        contentType != null -> KEditorPane(contentType, text)
        else -> KEditorPane()
    }.apply(block)
    add(txt)
    return txt
}

fun JToolBar.spinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner {
    val sp = (if (model == null) JSpinner() else JSpinner(model)).apply(block)
    add(sp)
    return sp
}

fun <T> JToolBar.list(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> {
    val list = when {
        model != null -> JList(model)
        array != null -> JList(array)
        vector != null -> JList(vector)
        else -> JList()
    }.apply(block)
    add(list)
    return list
}

fun JToolBar.table(
    model: TableModel? = null,
    columnModel: TableColumnModel? = null,
    selectionModel: ListSelectionModel? = null,
    rows: Int = -1,
    cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null,
    vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null,
    arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable {
    val table = when {
        model != null -> JTable(model, columnModel, selectionModel)
        rows != -1 && cols != -1 -> JTable(rows, cols)
        vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
        arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
        else -> JTable()
    }.apply(block)
    add(table)
    return table
}

fun JToolBar.tree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null, block: JTree.() -> Unit
): JTree {
    val tree = when {
        model != null -> JTree(model)
        node != null -> JTree(node)
        array != null -> JTree(array)
        vector != null -> JTree(vector)
        hashtable != null -> JTree(hashtable)
        else -> JTree()
    }.apply(block)
    add(tree)
    return tree
}

fun JToolBar.progress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar {
    val p = JProgressBar(orient, min, max).apply(block)
    add(p)
    return p
}

fun JToolBar.scrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar {
    val sb = JScrollBar(orientation, value, extent, min, max).apply(block)
    add(sb)
    return sb
}

fun JToolBar.separator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator {
    val sp = JSeparator(orientation).apply(block)
    add(sp)
    return sp
}

fun JToolBar.slider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider {
    val sl = JSlider(orientation, min, max, value).apply(block)
    add(sl)
    return sl
}

fun JToolBar.box(axis: Int = 0, block: Box.() -> Unit): Box {
    val box = Box(axis).apply(block)
    add(box)
    return box
}

fun JToolBar.image(data: ByteArray? = null, img: Image? = null, filename: String? = null, location: URL? = null, block: StretchIcon.() -> Unit): StretchIcon {
    val icon = when {
        data != null -> StretchIcon(imageData = data)
        img != null -> StretchIcon(image = img)
        filename != null -> StretchIcon(filename = filename)
        location != null -> StretchIcon(location = location)
        else -> throw IllegalArgumentException("All image sources are empty.")
    }.apply(block)
    val lbl = JLabel(icon, JLabel.CENTER)
    add(lbl)
    return icon
}

fun JToolBar.svg(uri: URI? = null, file: File? = null, inputStream: InputStream? = null, block: JSVGCanvas.() -> Unit): JSVGCanvas {
    val canvas = JSVGCanvas()
    when {
        file != null -> canvas.uri = file.toURI().toString()
        else -> {
            val parser = XMLResourceDescriptor.getXMLParserClassName()
            val factory = SAXSVGDocumentFactory(parser)
            canvas.svgDocument = when {
                uri != null -> factory.createSVGDocument(uri.toString())
                inputStream != null -> factory.createSVGDocument("", inputStream)
                else -> throw IllegalArgumentException("All image sources are empty.")
            }
        }
    }
    canvas.apply(block)
    add(canvas)
    return canvas
}

inline fun <reified T : Component> JToolBar.custom(vararg params: Any, block: T.() -> Unit): T {
    val comp = newClassInstance<T>(*params).apply(block)
    add(comp)
    return comp
}

fun <T : Component> JToolBar.comp(comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    add(comp)
    return comp
}