@file:Suppress("unused", "DuplicatedCode")

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
fun rootPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel = JPanel(layout).apply(block)

@ContextDsl
fun rootBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel = BorderPanel(hgap, vgap).apply(block)

@ContextDsl
fun rootClearPanel(block: ClearPanel.() -> Unit): ClearPanel = ClearPanel().apply(block)

@ContextDsl
fun rootVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)

@ContextDsl
fun rootHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel = HorzPanel(align, hgap, vgap).apply(block)

@ContextDsl
fun rootGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel = GridPanel(rows, cols, hgap, vgap).apply(block)

@ContextDsl
fun rootWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel = WrapPanel(align, hgap, vgap).apply(block)


fun JPanel.panel(layout: LayoutManager? = BorderLayout(), position: String? = null, block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.borderPanel(hgap: Int = 0, vgap: Int = 0, position: String? = null, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.clearPanel(position: String? = null, block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.vertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, position: String? = null, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.horzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, position: String? = null, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.gridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, position: String? = null, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.wrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, position: String? = null, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    if (position != null) add(pnl, position) else add(pnl)
    return pnl
}

fun JPanel.pager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, position: String? = null, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    if (position != null) add(pager, position) else add(pager)
    return pager
}

fun JPanel.scroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, position: String? = null, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    if (position != null) add(scroll, position) else add(scroll)
    return scroll
}

fun JPanel.split(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, position: String? = null, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    if (position != null) add(sp, position) else add(sp)
    return sp
}

fun JPanel.toolbar(orientation: Int = JToolBar.HORIZONTAL, position: String? = null, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    if (position != null) add(tb, position) else add(tb)
    return tb
}

fun JPanel.layer(position: String? = null, block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    if (position != null) add(lay, position) else add(lay)
    return lay
}

fun JPanel.label(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, position: String? = null, block: JLabel.() -> Unit): JLabel {
    val lbl = JLabel(title, icon, horizontalAlignment).apply(block)
    if (position != null) add(lbl, position) else add(lbl)
    return lbl
}

fun JPanel.input(text: String? = null, column: Int = 0, position: String? = null, block: KTextField.() -> Unit): KTextField {
    val txt = KTextField(text, column).apply(block)
    if (position != null) add(txt, position) else add(txt)
    return txt
}

fun <T> JPanel.combobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, position: String? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> {
    val cb = when {
        model != null -> JComboBox(model)
        array != null -> JComboBox(array)
        vector != null -> JComboBox(vector)
        else -> JComboBox()
    }.apply(block)
    if (position != null) add(cb, position) else add(cb)
    return cb
}

fun JPanel.button(title: String? = null, icon: Icon? = null, position: String? = null, block: JButton.() -> Unit): JButton {
    val b = JButton(title, icon).apply(block)
    if (position != null) add(b, position) else add(b)
    return b
}

fun JPanel.checkbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String? = null, block: JCheckBox.() -> Unit): JCheckBox {
    val chk = JCheckBox(title, icon, selected).apply(block)
    if (position != null) add(chk, position) else add(chk)
    return chk
}

fun JPanel.radio(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String? = null, block: JRadioButton.() -> Unit): JRadioButton {
    val rd = JRadioButton(title, icon, selected).apply(block)
    if (position != null) add(rd, position) else add(rd)
    return rd
}

fun JPanel.toggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String? = null, block: JToggleButton.() -> Unit): JToggleButton {
    val tg = JToggleButton(title, icon, selected).apply(block)
    if (position != null) add(tg, position) else add(tg)
    return tg
}

fun JPanel.textArea(text: String? = null, rows: Int = 0, columns: Int = 0, position: String? = null, block: KTextArea.() -> Unit): KTextArea {
    val ta = KTextArea(text, rows, columns).apply(block)
    if (position != null) add(ta, position) else add(ta)
    return ta
}

fun JPanel.inputPassword(text: String? = null, column: Int = 0, position: String? = null, block: KPasswordField.() -> Unit): KPasswordField {
    val txt = KPasswordField(text, column).apply(block)
    if (position != null) add(txt, position) else add(txt)
    return txt
}

fun JPanel.textPane(position: String? = null, block: KTextPane.() -> Unit): KTextPane {
    val txt = KTextPane().apply(block)
    if (position != null) add(txt, position) else add(txt)
    return txt
}

fun JPanel.editorPane(contentType: String? = null, text: String? = null, url: URL? = null, position: String? = null, block: KEditorPane.() -> Unit): KEditorPane {
    val txt = when {
        url != null -> KEditorPane(url)
        contentType != null -> KEditorPane(contentType, text)
        else -> KEditorPane()
    }.apply(block)
    if (position != null) add(txt, position) else add(txt)
    return txt
}

fun JPanel.spinner(model: SpinnerModel? = null, position: String? = null, block: JSpinner.() -> Unit): JSpinner {
    val sp = (if (model == null) JSpinner() else JSpinner(model)).apply(block)
    if (position != null) add(sp, position) else add(sp)
    return sp
}

fun <T> JPanel.list(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, position: String? = null, block: JList<T>.() -> Unit): JList<T> {
    val list = when {
        model != null -> JList(model)
        array != null -> JList(array)
        vector != null -> JList(vector)
        else -> JList()
    }.apply(block)
    if (position != null) add(list, position) else add(list)
    return list
}

fun JPanel.table(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    position: String? = null, block: JTable.() -> Unit
): JTable {
    val table = when {
        model != null -> JTable(model, columnModel, selectionModel)
        rows != -1 && cols != -1 -> JTable(rows, cols)
        vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
        arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
        else -> JTable()
    }.apply(block)
    if (position != null) add(table, position) else add(table)
    return table
}

fun JPanel.tree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    position: String? = null, block: JTree.() -> Unit
): JTree {
    val tree = when {
        model != null -> JTree(model)
        node != null -> JTree(node)
        array != null -> JTree(array)
        vector != null -> JTree(vector)
        hashtable != null -> JTree(hashtable)
        else -> JTree()
    }.apply(block)
    if (position != null) add(tree, position) else add(tree)
    return tree
}

fun JPanel.progress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, position: String? = null, block: JProgressBar.() -> Unit): JProgressBar {
    val p = JProgressBar(orient, min, max).apply(block)
    if (position != null) add(p, position) else add(p)
    return p
}

fun JPanel.scrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, position: String? = null, block: JScrollBar.() -> Unit): JScrollBar {
    val sb = JScrollBar(orientation, value, extent, min, max).apply(block)
    if (position != null) add(sb, position) else add(sb)
    return sb
}

fun JPanel.separator(orientation: Int = JSeparator.HORIZONTAL, position: String? = null, block: JSeparator.() -> Unit): JSeparator {
    val sp = JSeparator(orientation).apply(block)
    if (position != null) add(sp, position) else add(sp)
    return sp
}

fun JPanel.slider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, position: String? = null, block: JSlider.() -> Unit): JSlider {
    val sl = JSlider(orientation, min, max, value).apply(block)
    if (position != null) add(sl, position) else add(sl)
    return sl
}

fun JPanel.box(axis: Int = 0, position: String? = null, block: Box.() -> Unit): Box {
    val box = Box(axis).apply(block)
    if (position != null) add(box, position) else add(box)
    return box
}

inline fun<reified T: Component> JPanel.custom(position: String? = null, vararg params: Any, block: T.() -> Unit): T {
    val comp = newClassInstance<T>(*params).apply(block)
    if (position != null) add(comp, position) else add(comp)
    return comp
}

fun<T: Component> JPanel.comp(position: String? = null, comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    if (position != null) add(comp, position) else add(comp)
    return comp
}

fun JPanel.browser(position: String? = null, block: Browser.() -> Unit): Browser {
    val b = Browser().apply(block)
    if (position != null) add(b, position) else add(b)
    b.render()
    return b
}