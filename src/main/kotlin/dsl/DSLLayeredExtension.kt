@file:Suppress("unused", "DuplicatedCode")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import com.isyscore.kotlin.swing.inline.newClassInstance
import java.awt.BorderLayout
import java.awt.Component
import java.awt.LayoutManager
import java.net.URL
import java.util.*
import javax.swing.*
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

@ContextDsl
fun rootLayer(block: JLayeredPane.() -> Unit): JLayeredPane = JLayeredPane().apply(block)

fun JLayeredPane.panel(layout: LayoutManager? = BorderLayout(), layer: Int = 0, position: Int = 0, block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    add(pnl, layer, position)
    return pnl
}

fun JLayeredPane.borderPanel(layer: Int = 0, position: Int = 0, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel().apply(block)
    add(pnl, Integer(layer), position)
    return pnl
}

fun JLayeredPane.clearPanel(layer: Int = 0, position: Int = 0, block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    add(pnl, Integer(layer), position)
    return pnl
}

fun JLayeredPane.vertPanel(layer: Int = 0, position: Int = 0, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel().apply(block)
    add(pnl, Integer(layer), position)
    return pnl
}

fun JLayeredPane.horzPanel(layer: Int = 0, position: Int = 0, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel().apply(block)
    add(pnl, Integer(layer), position)
    return pnl
}

fun JLayeredPane.pager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, layer: Int = 0, position: Int = 0, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    add(pager, Integer(layer), position)
    return pager
}

fun JLayeredPane.scroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, layer: Int = 0, position: Int = 0, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    add(scroll, Integer(layer), position)
    return scroll
}

fun JLayeredPane.split(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, layer: Int = 0, position: Int = 0, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    add(sp, Integer(layer), position)
    return sp
}

fun JLayeredPane.toolbar(orientation: Int = JToolBar.HORIZONTAL, layer: Int = 0, position: Int = 0, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    add(tb, Integer(layer), position)
    return tb
}

fun JLayeredPane.layer(layer: Int = 0, position: Int = 0, block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    add(lay, Integer(layer), position)
    return lay
}

fun JLayeredPane.label(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, layer: Int = 0, position: Int = 0, block: JLabel.() -> Unit): JLabel {
    val lbl = JLabel(title, icon, horizontalAlignment).apply(block)
    add(lbl, Integer(layer), position)
    return lbl
}

fun JLayeredPane.input(text: String? = null, column: Int = 0, layer: Int = 0, position: Int = 0, block: KTextField.() -> Unit): KTextField {
    val txt = KTextField(text, column).apply(block)
    add(txt, Integer(layer), position)
    return txt
}

fun <T> JLayeredPane.combobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, layer: Int = 0, position: Int = 0, block: JComboBox<T>.() -> Unit): JComboBox<T> {
    val cb = when {
        model != null -> JComboBox(model)
        array != null -> JComboBox(array)
        vector != null -> JComboBox(vector)
        else -> JComboBox()
    }.apply(block)
    add(cb, Integer(layer), position)
    return cb
}

fun JLayeredPane.button(title: String? = null, icon: Icon? = null, layer: Int = 0, position: Int = 0, block: JButton.() -> Unit): JButton {
    val b = JButton(title, icon).apply(block)
    add(b, Integer(layer), position)
    return b
}

fun JLayeredPane.checkbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, layer: Int = 0, position: Int = 0, block: JCheckBox.() -> Unit): JCheckBox {
    val chk = JCheckBox(title, icon, selected).apply(block)
    add(chk, Integer(layer), position)
    return chk
}

fun JLayeredPane.radio(title: String? = null, icon: Icon? = null, selected: Boolean = false, layer: Int = 0, position: Int = 0, block: JRadioButton.() -> Unit): JRadioButton {
    val rd = JRadioButton(title, icon, selected).apply(block)
    add(rd, Integer(layer), position)
    return rd
}

fun JLayeredPane.toggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, layer: Int = 0, position: Int = 0, block: JToggleButton.() -> Unit): JToggleButton {
    val tg = JToggleButton(title, icon, selected).apply(block)
    add(tg, Integer(layer), position)
    return tg
}

fun JLayeredPane.textArea(text: String? = null, rows: Int = 0, columns: Int = 0, layer: Int = 0, position: Int = 0, block: KTextArea.() -> Unit): KTextArea {
    val ta = KTextArea(text, rows, columns).apply(block)
    add(ta, Integer(layer), position)
    return ta
}

fun JLayeredPane.inputPassword(text: String? = null, column: Int = 0, layer: Int = 0, position: Int = 0, block: KPasswordField.() -> Unit): KPasswordField {
    val txt = KPasswordField(text, column).apply(block)
    add(txt, Integer(layer), position)
    return txt
}

fun JLayeredPane.textPane(layer: Int = 0, position: Int = 0, block: KTextPane.() -> Unit): KTextPane {
    val txt = KTextPane().apply(block)
    add(txt, Integer(layer), position)
    return txt
}

fun JLayeredPane.editorPane(contentType: String? = null, text: String? = null, url: URL? = null, layer: Int = 0, position: Int = 0, block: KEditorPane.() -> Unit): KEditorPane {
    val txt = when {
        url != null -> KEditorPane(url)
        contentType != null -> KEditorPane(contentType, text)
        else -> KEditorPane()
    }.apply(block)
    add(txt, Integer(layer), position)
    return txt
}

fun JLayeredPane.spinner(model: SpinnerModel? = null, layer: Int = 0, position: Int = 0, block: JSpinner.() -> Unit): JSpinner {
    val sp = (if (model == null) JSpinner() else JSpinner(model)).apply(block)
    add(sp, Integer(layer), position)
    return sp
}

fun <T> JLayeredPane.list(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, layer: Int = 0, position: Int = 0, block: JList<T>.() -> Unit): JList<T> {
    val list = when {
        model != null -> JList(model)
        array != null -> JList(array)
        vector != null -> JList(vector)
        else -> JList()
    }.apply(block)
    add(list, Integer(layer), position)
    return list
}

fun JLayeredPane.table(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    layer: Int = 0, position: Int = 0, block: JTable.() -> Unit
): JTable {
    val table = when {
        model != null -> JTable(model, columnModel, selectionModel)
        rows != -1 && cols != -1 -> JTable(rows, cols)
        vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
        arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
        else -> JTable()
    }.apply(block)
    add(table, Integer(layer), position)
    return table
}

fun JLayeredPane.tree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    layer: Int = 0, position: Int = 0, block: JTree.() -> Unit
): JTree {
    val tree = when {
        model != null -> JTree(model)
        node != null -> JTree(node)
        array != null -> JTree(array)
        vector != null -> JTree(vector)
        hashtable != null -> JTree(hashtable)
        else -> JTree()
    }.apply(block)
    add(tree, Integer(layer), position)
    return tree
}

fun JLayeredPane.progress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, layer: Int = 0, position: Int = 0, block: JProgressBar.() -> Unit): JProgressBar {
    val p = JProgressBar(orient, min, max).apply(block)
    add(p, Integer(layer), position)
    return p
}

fun JLayeredPane.scrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, layer: Int = 0, position: Int = 0, block: JScrollBar.() -> Unit): JScrollBar {
    val sb = JScrollBar(orientation, value, extent, min, max).apply(block)
    add(sb, Integer(layer), position)
    return sb
}

fun JLayeredPane.separator(orientation: Int = JSeparator.HORIZONTAL, layer: Int = 0, position: Int = 0, block: JSeparator.() -> Unit): JSeparator {
    val sp = JSeparator(orientation).apply(block)
    add(sp, Integer(layer), position)
    return sp
}

fun JLayeredPane.slider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, layer: Int = 0, position: Int = 0, block: JSlider.() -> Unit): JSlider {
    val sl = JSlider(orientation, min, max, value).apply(block)
    add(sl, Integer(layer), position)
    return sl
}

fun JLayeredPane.box(axis: Int = 0, layer: Int = 0, position: Int = 0, block: Box.() -> Unit): Box {
    val box = Box(axis).apply(block)
    add(box, layer, position)
    return box
}

inline fun<reified T: Component> JLayeredPane.custom(layer: Int = 0, position: Int = 0, vararg params: Any, block: T.() -> Unit): T {
    val comp = newClassInstance<T>(*params).apply(block)
    add(comp, Integer(layer), position)
    return comp
}

fun<T: Component> JLayeredPane.comp(layer: Int = 0, position: Int = 0, comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    add(comp, Integer(layer), position)
    return comp
}
