@file:Suppress("unused")

package com.isyscore.kotlin.swing.dsl

import java.awt.Component
import java.util.*
import javax.swing.*
import javax.swing.event.CellEditorListener
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.tree.TreeCellRenderer

open class KDefaultCellRender(val block: JLabel.(value: Any?, index: Int, selected: Boolean, cellHasFocus: Boolean) -> Unit) : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(list: JList<*>, value: Any?, index: Int, selected: Boolean, cellHasFocus: Boolean): Component =
        super.getListCellRendererComponent(list, value, index, selected, cellHasFocus).apply {
            block(this as JLabel, value, index, selected, cellHasFocus)
        }
}

open class KCellRender<C : JComponent, T>(private val cls: Class<C>, val block: C.(value: T?, index: Int, selected: Boolean, cellHasFocus: Boolean) -> Unit) : ListCellRenderer<T> {
    override fun getListCellRendererComponent(list: JList<out T>, value: T?, index: Int, selected: Boolean, cellHasFocus: Boolean): Component =
        cls.getDeclaredConstructor().newInstance().apply {
            block(this, value, index, selected, cellHasFocus)
        }
}

open class KDefaultTableCell(val block: JLabel.(event: KDefaultTableCell, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int) -> Unit) : DefaultTableCellRenderer(), TableCellEditor {

    private var tmpValue: Any? = null
    fun setEditValue(v: Any?) {
        tmpValue = v
    }

    override fun getTableCellEditorComponent(table: JTable, value: Any?, selected: Boolean, row: Int, col: Int): Component {
        tmpValue = value
        return super.getTableCellRendererComponent(table, value, selected, true, row, col).apply {
            block(this as JLabel, this@KDefaultTableCell, value, selected, true, row, col)
        }
    }

    override fun getTableCellRendererComponent(table: JTable, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int): Component =
        super.getTableCellRendererComponent(table, value, selected, cellHasFocus, row, col).apply {
            block(this as JLabel, this@KDefaultTableCell, value, selected, cellHasFocus, row, col)
        }

    override fun getCellEditorValue(): Any? = tmpValue

    override fun isCellEditable(eo: EventObject): Boolean = true

    override fun shouldSelectCell(eo: EventObject): Boolean = false

    override fun stopCellEditing(): Boolean = true

    override fun cancelCellEditing() {}

    override fun addCellEditorListener(l: CellEditorListener) {}

    override fun removeCellEditorListener(l: CellEditorListener) {}

}

open class KTableCellRE<T : JComponent>(private val cls: Class<T>, val block: T.(event: KTableCellRE<T>, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int) -> Unit) : TableCellRenderer, TableCellEditor {

    private var table: JTable? = null
    private var tmpValue: Any? = null
    fun setEditValue(v: Any?, row: Int, col: Int) {
        tmpValue = v
        table?.model?.setValueAt(tmpValue, row, col)
    }

    override fun getTableCellRendererComponent(table: JTable, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int): Component {
        this.table = table
        return cls.getDeclaredConstructor().newInstance().apply {
            block(this, this@KTableCellRE, value, selected, cellHasFocus, row, col)
        }
    }

    override fun getTableCellEditorComponent(table: JTable, value: Any?, selected: Boolean, row: Int, col: Int): Component {
        this.table = table
        println("getTableCellEditorComponent => $value")
        tmpValue = value
        return cls.getDeclaredConstructor().newInstance().apply {
            block(this, this@KTableCellRE, value, selected, true, row, col)
        }
    }

    override fun getCellEditorValue(): Any? {
        println("getCellEditorValue => $tmpValue")
        return tmpValue
    }

    override fun isCellEditable(eo: EventObject): Boolean = true

    override fun shouldSelectCell(eo: EventObject): Boolean = false

    override fun stopCellEditing(): Boolean = true

    override fun cancelCellEditing() {}

    override fun addCellEditorListener(l: CellEditorListener) { }

    override fun removeCellEditorListener(l: CellEditorListener) {}

}

open class KDefaultTreeCellRender(val block: JLabel.(value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean) -> Unit) : DefaultTreeCellRenderer() {
    override fun getTreeCellRendererComponent(tree: JTree, value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean): Component =
        super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused).apply {
            block(this as JLabel, value, selected, expanded, isLeaf, row, focused)
        }
}

open class KTreeCellRender<T : JComponent>(private val cls: Class<T>, val block: T.(value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean) -> Unit) : TreeCellRenderer {
    override fun getTreeCellRendererComponent(tree: JTree, value: Any?, selected: Boolean, expanded: Boolean, isLeaf: Boolean, row: Int, focused: Boolean): Component =
        cls.getDeclaredConstructor().newInstance().apply {
            block(this, value, selected, expanded, isLeaf, row, focused)
        }
}
