package com.isyscore.kotlin.swing.component

import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JPanel

open class ClearPanel: JPanel(null)
open class BorderPanel(hgap: Int = 0, vgap: Int = 0): JPanel(BorderLayout(hgap, vgap))
open class VertPanel(align: Int = VertFlowLayout.TOP, hgap: Int = 4, vgap: Int = 4, hfill: Boolean = true, vfill: Boolean = false): JPanel(VertFlowLayout(align, hgap, vgap, hfill, vfill))
open class HorzPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5): JPanel(FlowLayout(align, hgap, vgap))
open class GridPanel(rows: Int, cols: Int, hgap: Int = 0, vgap: Int = 0): JPanel(GridLayout(rows, cols, hgap, vgap))
open class WrapPanel(align: Int = FlowLayout.CENTER, hgap: Int = 5, vgap: Int = 5): JPanel(WrapLayout(align, hgap, vgap))