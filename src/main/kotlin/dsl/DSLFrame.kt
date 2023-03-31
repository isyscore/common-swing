package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import java.awt.*
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JPanel

@ContextDsl
fun rootFrame(title: String? = null, gc: GraphicsConfiguration? = null, block: JFrame.() -> Unit): JFrame = JFrame(title, gc).apply(block)

@ContextDsl
fun rootDialog(owner: Window, title: String? = null, modType: Dialog.ModalityType = Dialog.ModalityType.MODELESS, gc: GraphicsConfiguration? = null, block: JDialog.() -> Unit): JDialog = JDialog(owner, title, modType, gc).apply(block)

@ContextDsl
fun rootWindow(owner: Window, gc: GraphicsConfiguration? = null, block: Window.() -> Unit): Window = Window(owner, gc).apply(block)

fun JFrame.contentPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentClearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentBorderPanel(hgap: Int = 0, vgap: Int = 0, block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel(hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentClearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentVertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false, block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel(align, hgap, vgap, hfill, vfill).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentHorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5, block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel(align, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentGridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0, block: GridPanel.() -> Unit): GridPanel {
    val pnl = GridPanel(rows, cols, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentWrapPanel(align: Int = FlowLayout.LEFT, hgap: Int = 5, vgap: Int = 5, block: WrapPanel.() -> Unit): WrapPanel {
    val pnl = WrapPanel(align, hgap, vgap).apply(block)
    contentPane = pnl
    return pnl
}