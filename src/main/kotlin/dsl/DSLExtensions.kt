package com.isyscore.kotlin.swing.dsl

import java.awt.*
import javax.swing.*
import javax.swing.border.Border
import javax.swing.plaf.ComponentUI

infix fun Int.x(y: Int): Dimension = Dimension(this, y)
infix fun Dimension.and(b: Dimension): Rectangle = Rectangle(this.width, this.height, b.width, b.height)

fun JComponent.bounds(block: () -> Rectangle) {
    val t = block()
    this.setBounds(t.x, t.y, t.width, t.height)
}

fun JComponent.size(block: () -> Dimension) {
    this.preferredSize = block()
}

fun JComponent.maxSize(block: () -> Dimension) {
    this.maximumSize = block()
}

fun JComponent.minSize(block: () -> Dimension) {
    this.minimumSize = block()
}

fun JComponent.border(block: () -> Border) {
    this.border = block()
}

fun JComponent.ui(block: () -> ComponentUI) {
    val mUI = this::class.java.getDeclaredMethod("setUI", ComponentUI::class.java).apply { isAccessible = true }
    mUI.invoke(this, block())
}

fun Window.size(block: () -> Dimension) {
    val d = block()
    this.setSize(d.width, d.height)
}

inline fun<reified C: JComponent ,T> JList<T>.cell(noinline block: C.(value: T?, index: Int, selected: Boolean, cellHasFocus: Boolean) -> Unit) {
    cellRenderer = KCellRender(C::class.java, block)
}

fun<T> JList<T>.defaultCell(block: JLabel.(value: Any?, index: Int, selected: Boolean, cellHasFocus: Boolean) -> Unit) {
    cellRenderer = KDefaultCellRender(block)
}

inline fun<reified T: JComponent> JTable.cell(colIndex: Int = columnCount - 1, noinline block: T.(cell: KTableCellRE<T>, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int) -> Unit) {
    val re = KTableCellRE(T::class.java, block)
    columnModel.getColumn(colIndex).apply {
        cellRenderer = re
        cellEditor = re
    }
}

fun JTable.defaultCell(colIndex: Int, block: JLabel.(cell: KDefaultTableCell, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int) -> Unit) {
    val re = KDefaultTableCell(block)
    columnModel.getColumn(colIndex).apply {
        cellRenderer = re
        cellEditor = re
    }
}

inline fun<reified T: JComponent> JTree.cell(noinline block: T.(value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean) -> Unit) {
    cellRenderer = KTreeCellRender(T::class.java, block)
}

fun JTree.defaultCell(block: JLabel.(value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean) -> Unit) {
    cellRenderer = KDefaultTreeCellRender(block)
}

