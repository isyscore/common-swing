package com.isyscore.kotlin.swing.component

import java.awt.Component
import java.util.*
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.event.CellEditorListener
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer

open class KDefaultReadonlyTableCell() : DefaultTableCellRenderer(), TableCellEditor {

    private var tmpValue: Any? = null

    override fun getTableCellEditorComponent(table: JTable, value: Any?, selected: Boolean, row: Int, col: Int): Component {
        tmpValue = value
        return super.getTableCellRendererComponent(table, value, selected, true, row, col)
    }

    override fun getTableCellRendererComponent(table: JTable, value: Any?, selected: Boolean, cellHasFocus: Boolean, row: Int, col: Int): Component =
        super.getTableCellRendererComponent(table, value, selected, cellHasFocus, row, col)

    override fun getCellEditorValue(): Any? = tmpValue

    override fun isCellEditable(eo: EventObject): Boolean = false

    override fun shouldSelectCell(eo: EventObject): Boolean = false

    override fun stopCellEditing(): Boolean = true

    override fun cancelCellEditing() {}

    override fun addCellEditorListener(l: CellEditorListener) {}

    override fun removeCellEditorListener(l: CellEditorListener) {}

}

class KDefaultBooleanRender : JCheckBox(), TableCellRenderer {
    override fun getTableCellRendererComponent(table: JTable, value: Any?, isSelected: Boolean, hasFocus: Boolean, row: Int, col: Int): Component {
        setSelected(value == true)
        return this
    }
}