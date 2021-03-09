@file:Suppress("unused", "DuplicatedCode")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import com.isyscore.kotlin.swing.inline.newClassInstance
import java.awt.*
import java.util.*
import javax.swing.*
import javax.swing.border.EmptyBorder

@ContextDsl
fun rootPager(tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)

fun JTabbedPane.addTabWithClose(title: String? = null, icon: Icon? = null, comp: Component) {
    fun makeClosePane(title: String?, comp: Component): BorderPanel = rootBorderPanel {
        border = EmptyBorder(0, 0, 0, 0)
        isOpaque = false
        borderPanel(position = BorderLayout.EAST) {
            isOpaque = false
            label(position = BorderLayout.NORTH) { isOpaque = false; preferredSize = Dimension(16, 4) }
            label(position = BorderLayout.SOUTH) { isOpaque = false; preferredSize = Dimension(16, 4) }
            button(title = "x", position = BorderLayout.CENTER) {
                isFocusPainted = false
                margin = Insets(0, 0, 0, 0)
                size { 16 x 16 }
                putClientProperty("JButton.buttonType", "square")
                addActionListener {
                    this@addTabWithClose.remove(this@addTabWithClose.indexOfComponent(comp))
                }
            }
        }
        label(title = "$title ", position = BorderLayout.WEST) {
            isOpaque = false
        }
    }
    addTab(title, icon, comp)
    val idx = indexOfComponent(comp)
    setTabComponentAt(idx, makeClosePane(title, comp))
}

fun JTabbedPane.panelTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    if (canClose) addTabWithClose(title, icon, pnl) else addTab(title, icon, pnl)
    return pnl
}

fun JTabbedPane.borderPanelTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel().apply(block)
    if (canClose) addTabWithClose(title, icon, pnl) else addTab(title, icon, pnl)
    return pnl
}

fun JTabbedPane.clearPanelTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    if (canClose) addTabWithClose(title, icon, pnl) else addTab(title, icon, pnl)
    return pnl
}

fun JTabbedPane.vertPanelTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel().apply(block)
    if (canClose) addTabWithClose(title, icon, pnl) else addTab(title, icon, pnl)
    return pnl
}

fun JTabbedPane.horzPanelTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel().apply(block)
    if (canClose) addTabWithClose(title, icon, pnl) else addTab(title, icon, pnl)
    return pnl
}

fun JTabbedPane.pagerTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, tabPlacement: Int = JTabbedPane.TOP, tabLayoutPolicy: Int = JTabbedPane.SCROLL_TAB_LAYOUT, block: JTabbedPane.() -> Unit): JTabbedPane {
    val pager = JTabbedPane(tabPlacement, tabLayoutPolicy).apply(block)
    if (canClose) addTabWithClose(title, icon, pager) else addTab(title, icon, pager)
    return pager
}

fun JTabbedPane.scrollerTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, vsbPolicy: Int = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, hsbPolicy: Int = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, block: JScrollPane.() -> Unit): JScrollPane {
    val scroll = JScrollPane(vsbPolicy, hsbPolicy).apply(block)
    if (canClose) addTabWithClose(title, icon, scroll) else addTab(title, icon, scroll)
    return scroll
}

fun JTabbedPane.splitTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, orientation: Int = JSplitPane.HORIZONTAL_SPLIT, block: JSplitPane.() -> Unit): JSplitPane {
    val sp = JSplitPane(orientation).apply(block)
    if (canClose) addTabWithClose(title, icon, sp) else addTab(title, icon, sp)
    return sp
}

fun JTabbedPane.toolbarTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, orientation: Int = JToolBar.HORIZONTAL, block: JToolBar.() -> Unit): JToolBar {
    val tb = JToolBar(orientation).apply(block)
    if (canClose) addTabWithClose(title, icon, tb) else addTab(title, icon, tb)
    return tb
}

fun JTabbedPane.layerTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, block: JLayeredPane.() -> Unit): JLayeredPane {
    val lay = JLayeredPane().apply(block)
    if (canClose) addTabWithClose(title, icon, lay) else addTab(title, icon, lay)
    return lay
}

inline fun<reified T: Component> JTabbedPane.customTab(title: String? = null, icon: Icon? = null, canClose: Boolean = false, vararg params: Any, block: T.() -> Unit): T {
    val lay = newClassInstance<T>(*params).apply(block)
    if (canClose) addTabWithClose(title, icon, lay) else addTab(title, icon, lay)
    return lay
}

fun<T: Component> JTabbedPane.compTab(title: String?, icon: Icon? = null, canClose: Boolean = false, comp: T, block: T.() -> Unit): T {
    comp.apply(block)
    if (canClose) addTabWithClose(title, icon, comp) else addTab(title, icon, comp)
    return comp
}