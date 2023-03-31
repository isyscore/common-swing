@file:Suppress("DuplicatedCode", "unused")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import com.isyscore.kotlin.swing.inline.newClassInstance
import java.awt.BorderLayout
import java.awt.Component
import java.awt.FlowLayout
import java.awt.LayoutManager
import java.net.URL
import java.util.*
import javax.swing.*
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

@ContextDsl
fun rootScrollPane(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane = JScrollPane(vsbPolicy, hsbPolicy).apply(block)

fun JScrollPane.panel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.borderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.clearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.vertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.horzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.gridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.wrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    setViewportView(pnl)
    return pnl
}

fun JScrollPane.pager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    setViewportView(pager)
    return pager
}

fun JScrollPane.scroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    setViewportView(scroll)
    return scroll
}

fun JScrollPane.split(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    setViewportView(sp)
    return sp
}

fun JScrollPane.toolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    setViewportView(tb)
    return tb
}

fun JScrollPane.layer(block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    setViewportView(lay)
    return lay
}

fun JScrollPane.label(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel {
    val lbl = JLabel(title, icon, horizontalAlignment).apply(block)
    setViewportView(lbl)
    return lbl
}

fun JScrollPane.input(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField {
    val txt = KTextField(text, column).apply(block)
    setViewportView(txt)
    return txt
}

fun <T> JScrollPane.combobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> {
    val cb = when {
        model != null -> JComboBox(model)
        array != null -> JComboBox(array)
        vector != null -> JComboBox(vector)
        else -> JComboBox()
    }.apply(block)
    setViewportView(cb)
    return cb
}

fun JScrollPane.button(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton {
    val b = JButton(title, icon).apply(block)
    setViewportView(b)
    return b
}

fun JScrollPane.checkbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox {
    val chk = JCheckBox(title, icon, selected).apply(block)
    setViewportView(chk)
    return chk
}

fun JScrollPane.radio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton {
    val rd = JRadioButton(title, icon, selected).apply(block)
    setViewportView(rd)
    return rd
}

fun JScrollPane.toggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton {
    val tg = JToggleButton(title, icon, selected).apply(block)
    setViewportView(tg)
    return tg
}

fun JScrollPane.textArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea {
    val ta = KTextArea(text, rows, columns).apply(block)
    setViewportView(ta)
    return ta
}

fun JScrollPane.inputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField {
    val txt = KPasswordField(text, column).apply(block)
    setViewportView(txt)
    return txt
}

fun JScrollPane.textPane(block: KTextPane.() -> Unit): KTextPane {
    val txt = KTextPane().apply(block)
    setViewportView(txt)
    return txt
}

fun JScrollPane.editorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane {
    val txt = when {
        url != null -> KEditorPane(url)
        contentType != null -> KEditorPane(contentType, text)
        else -> KEditorPane()
    }.apply(block)
    setViewportView(txt)
    return txt
}

fun JScrollPane.spinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner {
    val sp = (if (model == null) JSpinner() else JSpinner(model)).apply(block)
    setViewportView(sp)
    return sp
}

fun <T> JScrollPane.list(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> {
    val list = when {
        model != null -> JList(model)
        array != null -> JList(array)
        vector != null -> JList(vector)
        else -> JList()
    }.apply(block)
    setViewportView(list)
    return list
}

fun JScrollPane.table(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable {
    val table = when {
        model != null -> JTable(model, columnModel, selectionModel)
        rows != -1 && cols != -1 -> JTable(rows, cols)
        vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
        arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
        else -> JTable()
    }.apply(block)
    setViewportView(table)
    return table
}

fun JScrollPane.tree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    block: JTree.() -> Unit
): JTree {
    val tree = when {
        model != null -> JTree(model)
        node != null -> JTree(node)
        array != null -> JTree(array)
        vector != null -> JTree(vector)
        hashtable != null -> JTree(hashtable)
        else -> JTree()
    }.apply(block)
    setViewportView(tree)
    return tree
}

fun JScrollPane.progress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar {
    val p = JProgressBar(orient, min, max).apply(block)
    setViewportView(p)
    return p
}

fun JScrollPane.scrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar {
    val sb = JScrollBar(orientation, value, extent, min, max).apply(block)
    setViewportView(sb)
    return sb
}

fun JScrollPane.separator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator {
    val sp = JSeparator(orientation).apply(block)
    setViewportView(sp)
    return sp
}

fun JScrollPane.slider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider {
    val sl = JSlider(orientation, min, max, value).apply(block)
    setViewportView(sl)
    return sl
}

fun JScrollPane.box(axis: Int = 0, block: Box.() -> Unit): Box {
    val box = Box(axis).apply(block)
    setViewportView(box)
    return box
}

inline fun<reified T: Component> JScrollPane.custom(vararg params: Any, block: T.() -> Unit): T {
    val comp = newClassInstance<T>(*params).apply(block)
    setViewportView(comp)
    return comp
}

fun <T: Component> JScrollPane.comp(comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    setViewportView(comp)
    return comp
}

