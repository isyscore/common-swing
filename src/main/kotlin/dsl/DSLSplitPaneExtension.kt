@file:Suppress("DuplicatedCode", "unused")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
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

fun rootSplitPane(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane = JSplitPane(orientation).apply(block)

fun JSplitPane.panel(layout: LayoutManager? = BorderLayout(), position: String = JSplitPane.LEFT, block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel = panel(layout, JSplitPane.LEFT, block)
fun JSplitPane.rightPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel = panel(layout, JSplitPane.RIGHT, block)
fun JSplitPane.topPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel = panel(layout, JSplitPane.TOP, block)
fun JSplitPane.bottomPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel = panel(layout, JSplitPane.BOTTOM, block)

fun JSplitPane.borderPanel(hgap: Int = 0, vgap: Int = 0, position: String = JSplitPane.LEFT, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel = borderPanel(hgap, vgap, JSplitPane.LEFT, block)
fun JSplitPane.rightBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel = borderPanel(hgap, vgap, JSplitPane.RIGHT, block)
fun JSplitPane.topBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel = borderPanel(hgap, vgap, JSplitPane.TOP, block)
fun JSplitPane.bottomBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel = borderPanel(hgap, vgap, JSplitPane.BOTTOM, block)

fun JSplitPane.clearPanel(position: String = JSplitPane.LEFT, block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftClearPanel(block: ClearPanel.() -> Unit): ClearPanel = clearPanel(JSplitPane.LEFT, block)
fun JSplitPane.rightClearPanel(block: ClearPanel.() -> Unit): ClearPanel = clearPanel(JSplitPane.RIGHT, block)
fun JSplitPane.topClearPanel(block: ClearPanel.() -> Unit): ClearPanel = clearPanel(JSplitPane.TOP, block)
fun JSplitPane.bottomClearPanel(block: ClearPanel.() -> Unit): ClearPanel = clearPanel(JSplitPane.BOTTOM, block)

fun JSplitPane.vertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, position: String = JSplitPane.LEFT, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel = vertPanel(align, hgap, vgap, hfill, vfill, JSplitPane.LEFT, block)
fun JSplitPane.rightVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel = vertPanel(align, hgap, vgap, hfill, vfill,JSplitPane.RIGHT, block)
fun JSplitPane.topVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel = vertPanel(align, hgap, vgap, hfill, vfill,JSplitPane.TOP, block)
fun JSplitPane.bottomVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel = vertPanel(align, hgap, vgap, hfill, vfill,JSplitPane.BOTTOM, block)


fun JSplitPane.horzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, position: String = JSplitPane.LEFT, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel = horzPanel(align, hgap, vgap, JSplitPane.LEFT, block)
fun JSplitPane.rightHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel = horzPanel(align, hgap, vgap, JSplitPane.RIGHT, block)
fun JSplitPane.topHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel = horzPanel(align, hgap, vgap, JSplitPane.TOP, block)
fun JSplitPane.bottomHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel = horzPanel(align, hgap, vgap, JSplitPane.BOTTOM, block)

fun JSplitPane.gridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, position: String = JSplitPane.LEFT, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel = gridPanel(rows, cols, hgap, vgap, JSplitPane.LEFT, block)
fun JSplitPane.rightGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel = gridPanel(rows, cols, hgap, vgap, JSplitPane.RIGHT, block)
fun JSplitPane.topGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel = gridPanel(rows, cols, hgap, vgap, JSplitPane.TOP, block)
fun JSplitPane.bottomGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel = gridPanel(rows, cols, hgap, vgap, JSplitPane.BOTTOM, block)


fun JSplitPane.wrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, position: String = JSplitPane.LEFT, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pnl
        JSplitPane.RIGHT -> rightComponent = pnl
        JSplitPane.TOP -> topComponent = pnl
        JSplitPane.BOTTOM -> bottomComponent = pnl
    }
    return pnl
}

fun JSplitPane.leftWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel = wrapPanel(align, hgap, vgap, JSplitPane.LEFT, block)
fun JSplitPane.rightWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel = wrapPanel(align, hgap, vgap, JSplitPane.RIGHT, block)
fun JSplitPane.topWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel = wrapPanel(align, hgap, vgap, JSplitPane.TOP, block)
fun JSplitPane.bottomWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel = wrapPanel(align, hgap, vgap, JSplitPane.BOTTOM, block)


fun JSplitPane.pager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, position: String = JSplitPane.LEFT, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = pager
        JSplitPane.RIGHT -> rightComponent = pager
        JSplitPane.TOP -> topComponent = pager
        JSplitPane.BOTTOM -> bottomComponent = pager
    }
    return pager
}

fun JSplitPane.leftPager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane = pager(tabPlacement, tabLayoutPolicy, JSplitPane.LEFT, block)
fun JSplitPane.rightPager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane = pager(tabPlacement, tabLayoutPolicy, JSplitPane.RIGHT, block)
fun JSplitPane.topPager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane = pager(tabPlacement, tabLayoutPolicy, JSplitPane.TOP, block)
fun JSplitPane.bottomPager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane = pager(tabPlacement, tabLayoutPolicy, JSplitPane.BOTTOM, block)

fun JSplitPane.scroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, position: String = JSplitPane.LEFT, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = scroll
        JSplitPane.RIGHT -> rightComponent = scroll
        JSplitPane.TOP -> topComponent = scroll
        JSplitPane.BOTTOM -> bottomComponent = scroll
    }
    return scroll
}

fun JSplitPane.leftScroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane = scroller(vsbPolicy, hsbPolicy, JSplitPane.LEFT, block)
fun JSplitPane.rightScroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane = scroller(vsbPolicy, hsbPolicy, JSplitPane.RIGHT, block)
fun JSplitPane.topScroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane = scroller(vsbPolicy, hsbPolicy, JSplitPane.TOP, block)
fun JSplitPane.bottomScroller(vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane = scroller(vsbPolicy, hsbPolicy, JSplitPane.BOTTOM, block)

fun JSplitPane.split(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, position: String = JSplitPane.LEFT, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = sp
        JSplitPane.RIGHT -> rightComponent = sp
        JSplitPane.TOP -> topComponent = sp
        JSplitPane.BOTTOM -> bottomComponent = sp
    }
    return sp
}

fun JSplitPane.leftSplit(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane = split(orientation, JSplitPane.LEFT, block)
fun JSplitPane.rightSplit(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane = split(orientation, JSplitPane.RIGHT, block)
fun JSplitPane.topSplit(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane = split(orientation, JSplitPane.TOP, block)
fun JSplitPane.bottomSplit(orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane = split(orientation, JSplitPane.BOTTOM, block)

fun JSplitPane.toolbar(orientation: Int = JToolBar.HORIZONTAL, position: String = JSplitPane.LEFT, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = tb
        JSplitPane.RIGHT -> rightComponent = tb
        JSplitPane.TOP -> topComponent = tb
        JSplitPane.BOTTOM -> bottomComponent = tb
    }
    return tb
}

fun JSplitPane.leftToolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar = toolbar(orientation, JSplitPane.LEFT, block)
fun JSplitPane.rightToolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar = toolbar(orientation, JSplitPane.RIGHT, block)
fun JSplitPane.topToolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar = toolbar(orientation, JSplitPane.TOP, block)
fun JSplitPane.bottomToolbar(orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar = toolbar(orientation, JSplitPane.BOTTOM, block)

fun JSplitPane.layer(position: String = JSplitPane.LEFT, block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = lay
        JSplitPane.RIGHT -> rightComponent = lay
        JSplitPane.TOP -> topComponent = lay
        JSplitPane.BOTTOM -> bottomComponent = lay
    }
    return lay
}

fun JSplitPane.leftLayer(block: JLayeredPane.() -> Unit): JLayeredPane = layer(JSplitPane.LEFT, block)
fun JSplitPane.rightLayer(block: JLayeredPane.() -> Unit): JLayeredPane = layer(JSplitPane.RIGHT, block)
fun JSplitPane.topLayer(block: JLayeredPane.() -> Unit): JLayeredPane = layer(JSplitPane.TOP, block)
fun JSplitPane.bottomLayer(block: JLayeredPane.() -> Unit): JLayeredPane = layer(JSplitPane.BOTTOM, block)

fun JSplitPane.label(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, position: String = JSplitPane.LEFT, block: JLabel.() -> Unit): JLabel {
    val lbl = JLabel(title, icon, horizontalAlignment).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = lbl
        JSplitPane.RIGHT -> rightComponent = lbl
        JSplitPane.TOP -> topComponent = lbl
        JSplitPane.BOTTOM -> bottomComponent = lbl
    }
    return lbl
}

fun JSplitPane.leftLabel(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel = label(title, icon, horizontalAlignment, JSplitPane.LEFT, block)
fun JSplitPane.rightLabel(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel = label(title, icon, horizontalAlignment, JSplitPane.RIGHT, block)
fun JSplitPane.topLabel(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel = label(title, icon, horizontalAlignment, JSplitPane.TOP, block)
fun JSplitPane.bottomLabel(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel = label(title, icon, horizontalAlignment, JSplitPane.BOTTOM, block)

fun JSplitPane.input(text: String? = null, column: Int = 0, position: String = JSplitPane.LEFT, block: KTextField.() -> Unit): KTextField {
    val txt = KTextField(text, column).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = txt
        JSplitPane.RIGHT -> rightComponent = txt
        JSplitPane.TOP -> topComponent = txt
        JSplitPane.BOTTOM -> bottomComponent = txt
    }
    return txt
}

fun JSplitPane.leftInput(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField = input(text, column, JSplitPane.LEFT, block)
fun JSplitPane.rightInput(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField = input(text, column, JSplitPane.RIGHT, block)
fun JSplitPane.topInput(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField = input(text, column, JSplitPane.TOP, block)
fun JSplitPane.bottomInput(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField = input(text, column, JSplitPane.BOTTOM, block)

fun <T> JSplitPane.combobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, position: String = JSplitPane.LEFT, block: JComboBox<T>.() -> Unit): JComboBox<T> {
    val cb = when {
        model != null -> JComboBox(model)
        array != null -> JComboBox(array)
        vector != null -> JComboBox(vector)
        else -> JComboBox()
    }.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = cb
        JSplitPane.RIGHT -> rightComponent = cb
        JSplitPane.TOP -> topComponent = cb
        JSplitPane.BOTTOM -> bottomComponent = cb
    }
    return cb
}

fun <T> JSplitPane.leftCombobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> = combobox(model, array, vector, JSplitPane.LEFT, block)
fun <T> JSplitPane.rightCombobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> = combobox(model, array, vector, JSplitPane.RIGHT, block)
fun <T> JSplitPane.topCombobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> = combobox(model, array, vector, JSplitPane.TOP, block)
fun <T> JSplitPane.bottomCombobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> = combobox(model, array, vector, JSplitPane.BOTTOM, block)

fun JSplitPane.button(title: String? = null, icon: Icon? = null, position: String = JSplitPane.LEFT, block: JButton.() -> Unit): JButton {
    val b = JButton(title, icon).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = b
        JSplitPane.RIGHT -> rightComponent = b
        JSplitPane.TOP -> topComponent = b
        JSplitPane.BOTTOM -> bottomComponent = b
    }
    return b
}

fun JSplitPane.leftButton(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton = button(title, icon, JSplitPane.LEFT, block)
fun JSplitPane.rightButton(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton = button(title, icon, JSplitPane.RIGHT, block)
fun JSplitPane.topButton(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton = button(title, icon, JSplitPane.TOP, block)
fun JSplitPane.bottomButton(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton = button(title, icon, JSplitPane.BOTTOM, block)

fun JSplitPane.checkbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String = JSplitPane.LEFT, block: JCheckBox.() -> Unit): JCheckBox {
    val chk = JCheckBox(title, icon, selected).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = chk
        JSplitPane.RIGHT -> rightComponent = chk
        JSplitPane.TOP -> topComponent = chk
        JSplitPane.BOTTOM -> bottomComponent = chk
    }
    return chk
}

fun JSplitPane.leftCheckbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox = checkbox(title, icon, selected, JSplitPane.LEFT, block)
fun JSplitPane.rightCheckbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox = checkbox(title, icon, selected, JSplitPane.RIGHT, block)
fun JSplitPane.topCheckbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox = checkbox(title, icon, selected, JSplitPane.TOP, block)
fun JSplitPane.bottomCheckbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox = checkbox(title, icon, selected, JSplitPane.BOTTOM, block)

fun JSplitPane.radio(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String = JSplitPane.LEFT, block: JRadioButton.() -> Unit): JRadioButton {
    val rd = JRadioButton(title, icon, selected).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = rd
        JSplitPane.RIGHT -> rightComponent = rd
        JSplitPane.TOP -> topComponent = rd
        JSplitPane.BOTTOM -> bottomComponent = rd
    }
    return rd
}

fun JSplitPane.leftRadio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton = radio(title, icon, selected, JSplitPane.LEFT, block)
fun JSplitPane.rightRadio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton = radio(title, icon, selected, JSplitPane.RIGHT, block)
fun JSplitPane.topRadio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton = radio(title, icon, selected, JSplitPane.TOP, block)
fun JSplitPane.bottomRadio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton = radio(title, icon, selected, JSplitPane.BOTTOM, block)

fun JSplitPane.toggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, position: String = JSplitPane.LEFT, block: JToggleButton.() -> Unit): JToggleButton {
    val tg = JToggleButton(title, icon, selected).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = tg
        JSplitPane.RIGHT -> rightComponent = tg
        JSplitPane.TOP -> topComponent = tg
        JSplitPane.BOTTOM -> bottomComponent = tg
    }
    return tg
}

fun JSplitPane.leftToggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton = toggle(title, icon, selected, JSplitPane.LEFT, block)
fun JSplitPane.rightToggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton = toggle(title, icon, selected, JSplitPane.RIGHT, block)
fun JSplitPane.topToggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton = toggle(title, icon, selected, JSplitPane.TOP, block)
fun JSplitPane.bottomToggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton = toggle(title, icon, selected, JSplitPane.BOTTOM, block)

fun JSplitPane.textArea(text: String? = null, rows: Int = 0, columns: Int = 0, position: String = JSplitPane.LEFT, block: KTextArea.() -> Unit): KTextArea {
    val ta = KTextArea(text, rows, columns).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = ta
        JSplitPane.RIGHT -> rightComponent = ta
        JSplitPane.TOP -> topComponent = ta
        JSplitPane.BOTTOM -> bottomComponent = ta
    }
    return ta
}

fun JSplitPane.leftTextArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea = textArea(text, rows, columns, JSplitPane.LEFT, block)
fun JSplitPane.rightTextArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea = textArea(text, rows, columns, JSplitPane.RIGHT, block)
fun JSplitPane.topTextArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea = textArea(text, rows, columns, JSplitPane.TOP, block)
fun JSplitPane.bottomTextArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea = textArea(text, rows, columns, JSplitPane.BOTTOM, block)

fun JSplitPane.inputPassword(text: String? = null, column: Int = 0, position: String = JSplitPane.LEFT, block: KPasswordField.() -> Unit): KPasswordField {
    val txt = KPasswordField(text, column).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = txt
        JSplitPane.RIGHT -> rightComponent = txt
        JSplitPane.TOP -> topComponent = txt
        JSplitPane.BOTTOM -> bottomComponent = txt
    }
    return txt
}

fun JSplitPane.leftInputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField = inputPassword(text, column, JSplitPane.LEFT, block)
fun JSplitPane.rightInputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField = inputPassword(text, column, JSplitPane.RIGHT, block)
fun JSplitPane.topInputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField = inputPassword(text, column, JSplitPane.TOP, block)
fun JSplitPane.bottomInputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField = inputPassword(text, column, JSplitPane.BOTTOM, block)

fun JSplitPane.textPane(position: String = JSplitPane.LEFT, block: KTextPane.() -> Unit): KTextPane {
    val txt = KTextPane().apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = txt
        JSplitPane.RIGHT -> rightComponent = txt
        JSplitPane.TOP -> topComponent = txt
        JSplitPane.BOTTOM -> bottomComponent = txt
    }
    return txt
}

fun JSplitPane.leftTextPane(block: KTextPane.() -> Unit): KTextPane = textPane(JSplitPane.LEFT, block)
fun JSplitPane.rightTextPane(block: KTextPane.() -> Unit): KTextPane = textPane(JSplitPane.RIGHT, block)
fun JSplitPane.topTextPane(block: KTextPane.() -> Unit): KTextPane = textPane(JSplitPane.TOP, block)
fun JSplitPane.bottomTextPane(block: KTextPane.() -> Unit): KTextPane = textPane(JSplitPane.BOTTOM, block)

fun JSplitPane.editorPane(contentType: String? = null, text: String? = null, url: URL? = null, position: String = JSplitPane.LEFT, block: KEditorPane.() -> Unit): KEditorPane {
    val txt = when {
        url != null -> KEditorPane(url)
        contentType != null -> KEditorPane(contentType, text)
        else -> KEditorPane()
    }.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = txt
        JSplitPane.RIGHT -> rightComponent = txt
        JSplitPane.TOP -> topComponent = txt
        JSplitPane.BOTTOM -> bottomComponent = txt
    }
    return txt
}

fun JSplitPane.leftEditorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane = editorPane(contentType, text, url, JSplitPane.LEFT, block)
fun JSplitPane.rightEditorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane = editorPane(contentType, text, url, JSplitPane.RIGHT, block)
fun JSplitPane.topEditorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane = editorPane(contentType, text, url, JSplitPane.TOP, block)
fun JSplitPane.bottomEditorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane = editorPane(contentType, text, url, JSplitPane.BOTTOM, block)

fun JSplitPane.spinner(model: SpinnerModel? = null, position: String = JSplitPane.LEFT, block: JSpinner.() -> Unit): JSpinner {
    val sp = (if (model == null) JSpinner() else JSpinner(model)).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = sp
        JSplitPane.RIGHT -> rightComponent = sp
        JSplitPane.TOP -> topComponent = sp
        JSplitPane.BOTTOM -> bottomComponent = sp
    }
    return sp
}

fun JSplitPane.leftSpinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner = spinner(model, JSplitPane.LEFT, block)
fun JSplitPane.rightSpinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner = spinner(model, JSplitPane.RIGHT, block)
fun JSplitPane.topSpinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner = spinner(model, JSplitPane.TOP, block)
fun JSplitPane.bottomSpinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner = spinner(model, JSplitPane.BOTTOM, block)

fun <T> JSplitPane.list(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, position: String = JSplitPane.LEFT, block: JList<T>.() -> Unit): JList<T> {
    val list = when {
        model != null -> JList(model)
        array != null -> JList(array)
        vector != null -> JList(vector)
        else -> JList()
    }.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = list
        JSplitPane.RIGHT -> rightComponent = list
        JSplitPane.TOP -> topComponent = list
        JSplitPane.BOTTOM -> bottomComponent = list
    }
    return list
}

fun <T> JSplitPane.leftList(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> = list(model, array, vector, JSplitPane.LEFT, block)
fun <T> JSplitPane.rightList(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> = list(model, array, vector, JSplitPane.RIGHT, block)
fun <T> JSplitPane.topList(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> = list(model, array, vector, JSplitPane.TOP, block)
fun <T> JSplitPane.bottomList(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> = list(model, array, vector, JSplitPane.BOTTOM, block)

fun JSplitPane.table(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    position: String = JSplitPane.LEFT, block: JTable.() -> Unit
): JTable {
    val table = when {
        model != null -> JTable(model, columnModel, selectionModel)
        rows != -1 && cols != -1 -> JTable(rows, cols)
        vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
        arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
        else -> JTable()
    }.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = table
        JSplitPane.RIGHT -> rightComponent = table
        JSplitPane.TOP -> topComponent = table
        JSplitPane.BOTTOM -> bottomComponent = table
    }
    return table
}

fun JSplitPane.leftTable(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable = table(model, columnModel, selectionModel, rows, cols, vecRowData, vecColumnNames, arrayRowData, arrayColumnNames, JSplitPane.LEFT, block)
fun JSplitPane.rightTable(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable = table(model, columnModel, selectionModel, rows, cols, vecRowData, vecColumnNames, arrayRowData, arrayColumnNames, JSplitPane.RIGHT, block)
fun JSplitPane.topTable(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable = table(model, columnModel, selectionModel, rows, cols, vecRowData, vecColumnNames, arrayRowData, arrayColumnNames, JSplitPane.TOP, block)
fun JSplitPane.bottomTable(
    model: TableModel? = null, columnModel: TableColumnModel? = null, selectionModel: ListSelectionModel? = null,
    rows: Int = -1, cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null, vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null, arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable = table(model, columnModel, selectionModel, rows, cols, vecRowData, vecColumnNames, arrayRowData, arrayColumnNames, JSplitPane.BOTTOM, block)

fun JSplitPane.tree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    position: String = JSplitPane.LEFT, block: JTree.() -> Unit
): JTree {
    val tree = when {
        model != null -> JTree(model)
        node != null -> JTree(node)
        array != null -> JTree(array)
        vector != null -> JTree(vector)
        hashtable != null -> JTree(hashtable)
        else -> JTree()
    }.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = tree
        JSplitPane.RIGHT -> rightComponent = tree
        JSplitPane.TOP -> topComponent = tree
        JSplitPane.BOTTOM -> bottomComponent = tree
    }
    return tree
}

fun JSplitPane.leftTree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    block: JTree.() -> Unit
): JTree = tree(model, node, array, vector, hashtable, JSplitPane.LEFT, block)
fun JSplitPane.rightTree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    block: JTree.() -> Unit
): JTree = tree(model, node, array, vector, hashtable, JSplitPane.RIGHT, block)
fun JSplitPane.topTree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    block: JTree.() -> Unit
): JTree = tree(model, node, array, vector, hashtable, JSplitPane.TOP, block)
fun JSplitPane.bottomTree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null,
    block: JTree.() -> Unit
): JTree = tree(model, node, array, vector, hashtable, JSplitPane.BOTTOM, block)

fun JSplitPane.progress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, position: String = JSplitPane.LEFT, block: JProgressBar.() -> Unit): JProgressBar {
    val p = JProgressBar(orient, min, max).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = p
        JSplitPane.RIGHT -> rightComponent = p
        JSplitPane.TOP -> topComponent = p
        JSplitPane.BOTTOM -> bottomComponent = p
    }
    return p
}

fun JSplitPane.leftProgress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar = progress(orient, min, max, JSplitPane.LEFT, block)
fun JSplitPane.rightProgress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar = progress(orient, min, max, JSplitPane.RIGHT, block)
fun JSplitPane.topProgress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar = progress(orient, min, max, JSplitPane.TOP, block)
fun JSplitPane.bottomProgress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar = progress(orient, min, max, JSplitPane.BOTTOM, block)

fun JSplitPane.scrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, position: String = JSplitPane.LEFT, block: JScrollBar.() -> Unit): JScrollBar {
    val sb = JScrollBar(orientation, value, extent, min, max).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = sb
        JSplitPane.RIGHT -> rightComponent = sb
        JSplitPane.TOP -> topComponent = sb
        JSplitPane.BOTTOM -> bottomComponent = sb
    }
    return sb
}

fun JSplitPane.leftScrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar = scrollbar(orientation, value, extent, min, max, JSplitPane.LEFT, block)
fun JSplitPane.rightScrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar = scrollbar(orientation, value, extent, min, max, JSplitPane.RIGHT, block)
fun JSplitPane.topScrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar = scrollbar(orientation, value, extent, min, max, JSplitPane.TOP, block)
fun JSplitPane.bottomScrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar = scrollbar(orientation, value, extent, min, max, JSplitPane.BOTTOM, block)

fun JSplitPane.separator(orientation: Int = JSeparator.HORIZONTAL, position: String = JSplitPane.LEFT, block: JSeparator.() -> Unit): JSeparator {
    val sp = JSeparator(orientation).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = sp
        JSplitPane.RIGHT -> rightComponent = sp
        JSplitPane.TOP -> topComponent = sp
        JSplitPane.BOTTOM -> bottomComponent = sp
    }
    return sp
}

fun JSplitPane.leftSeparator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator = separator(orientation, JSplitPane.LEFT, block)
fun JSplitPane.rightSeparator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator = separator(orientation, JSplitPane.RIGHT, block)
fun JSplitPane.topSeparator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator = separator(orientation, JSplitPane.TOP, block)
fun JSplitPane.bottomSeparator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator = separator(orientation, JSplitPane.BOTTOM, block)

fun JSplitPane.slider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, position: String = JSplitPane.LEFT, block: JSlider.() -> Unit): JSlider {
    val sl = JSlider(orientation, min, max, value).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = sl
        JSplitPane.RIGHT -> rightComponent = sl
        JSplitPane.TOP -> topComponent = sl
        JSplitPane.BOTTOM -> bottomComponent = sl
    }
    return sl
}

fun JSplitPane.leftSlider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider = slider(orientation, min, max, value, JSplitPane.LEFT, block)
fun JSplitPane.rightSlider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider = slider(orientation, min, max, value, JSplitPane.RIGHT, block)
fun JSplitPane.topSlider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider = slider(orientation, min, max, value, JSplitPane.TOP, block)
fun JSplitPane.bottomSlider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider = slider(orientation, min, max, value, JSplitPane.BOTTOM, block)

fun JSplitPane.box(axis: Int = 0, position: String = JSplitPane.LEFT, block: Box.() -> Unit): Box {
    val box = Box(axis).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = box
        JSplitPane.RIGHT -> rightComponent = box
        JSplitPane.TOP -> topComponent = box
        JSplitPane.BOTTOM -> bottomComponent = box
    }
    return box
}

fun JSplitPane.leftBox(axis: Int = 0, block: Box.() -> Unit): Box = box(axis, JSplitPane.LEFT, block)
fun JSplitPane.rightBox(axis: Int = 0, block: Box.() -> Unit): Box = box(axis, JSplitPane.RIGHT, block)
fun JSplitPane.topBox(axis: Int = 0, block: Box.() -> Unit): Box = box(axis, JSplitPane.TOP, block)
fun JSplitPane.bottomBox(axis: Int = 0, block: Box.() -> Unit): Box = box(axis, JSplitPane.BOTTOM, block)


inline fun<reified T: Component> JSplitPane.custom(position: String = JSplitPane.LEFT, vararg params: Any, block: T.() -> Unit): T {
    val comp = T::class.java.getDeclaredConstructor(*params.map { it::class.java }.toTypedArray()).apply { isAccessible = true }.newInstance(*params).apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = comp
        JSplitPane.RIGHT -> rightComponent = comp
        JSplitPane.TOP -> topComponent = comp
        JSplitPane.BOTTOM -> bottomComponent = comp
    }
    return comp
}

inline fun<reified T: Component> JSplitPane.leftCustom(vararg params: Any, block: T.() -> Unit): T = custom(JSplitPane.LEFT, *params) { block(this) }
inline fun<reified T: Component> JSplitPane.rightCustom(vararg params: Any, block: T.() -> Unit): T = custom(JSplitPane.RIGHT, *params) { block(this) }
inline fun<reified T: Component> JSplitPane.topCustom(vararg params: Any, block: T.() -> Unit): T = custom(JSplitPane.TOP, *params) { block(this) }
inline fun<reified T: Component> JSplitPane.bottomCustom(vararg params: Any, block: T.() -> Unit): T = custom(JSplitPane.BOTTOM, *params) { block(this) }

fun<T: Component> JSplitPane.comp(position: String = JSplitPane.LEFT, comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    when(position) {
        JSplitPane.LEFT -> leftComponent = comp
        JSplitPane.RIGHT -> rightComponent = comp
        JSplitPane.TOP -> topComponent = comp
        JSplitPane.BOTTOM -> bottomComponent = comp
    }
    return comp
}

fun <T: Component> JSplitPane.leftComp(comp: T, block: T.() -> Unit): T = comp(JSplitPane.LEFT, comp, block)
fun <T: Component> JSplitPane.rightComp(comp: T, block: T.() -> Unit): T = comp(JSplitPane.RIGHT, comp, block)
fun <T: Component> JSplitPane.topComp(comp: T, block: T.() -> Unit): T = comp(JSplitPane.TOP, comp, block)
fun <T: Component> JSplitPane.bottomComp(comp: T, block: T.() -> Unit): T = comp(JSplitPane.BOTTOM, comp, block)