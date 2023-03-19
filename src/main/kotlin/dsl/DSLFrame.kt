package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.BorderPanel
import com.isyscore.kotlin.swing.component.ClearPanel
import com.isyscore.kotlin.swing.component.HorzPanel
import com.isyscore.kotlin.swing.component.VertPanel
import java.awt.*
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLayeredPane
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

fun JFrame.contentBorderPanel(block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentClearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentVertPanel(block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JFrame.contentHorzPanel(block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentPanel(layout: LayoutManager? = BorderLayout(), block: JPanel.() -> Unit): JPanel {
    val pnl = JPanel(layout).apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentBorderPanel(block: BorderPanel.() -> Unit): BorderPanel {
    val pnl = BorderPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentClearPanel(block: ClearPanel.() -> Unit): ClearPanel {
    val pnl = ClearPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentVertPanel(block: VertPanel.() -> Unit): VertPanel {
    val pnl = VertPanel().apply(block)
    contentPane = pnl
    return pnl
}

fun JDialog.contentHorzPanel(block: HorzPanel.() -> Unit): HorzPanel {
    val pnl = HorzPanel().apply(block)
    contentPane = pnl
    return pnl
}