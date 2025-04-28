@file:Suppress("unused")
package com.isyscore.kotlin.swing.component

import java.awt.Container
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JScrollPane
import javax.swing.SwingUtilities
import kotlin.math.max

class WrapLayout : FlowLayout {

    constructor() : super()
    constructor(align: Int) : super(align)
    constructor(align: Int, hgap: Int, vgap: Int) : super(align, hgap, vgap)

    override fun preferredLayoutSize(target: Container): Dimension {
        return layoutSize(target, true)
    }

    override fun minimumLayoutSize(target: Container): Dimension {
        val minimum = layoutSize(target, false)
        minimum.width -= (hgap + 1)
        return minimum
    }

    private fun layoutSize(target: Container, preferred: Boolean): Dimension {
        synchronized(target.treeLock) {
            val targetWidth = target.size.width
            if (targetWidth == 0) {
                return Dimension()
            }
            val hg = hgap
            val vg = vgap
            val ins = target.insets
            val horizontalInsetsAndGap = ins.left + ins.right + (hg * 2)
            val maxWidth = targetWidth - horizontalInsetsAndGap
            val dim = Dimension(0, 0)
            var rowWidth = 0
            var rowHeight = 0
            val nmembers = target.componentCount
            for (i in 0 until nmembers) {
                val m = target.getComponent(i)
                if (m.isVisible) {
                    val d = if (preferred) m.preferredSize else m.minimumSize
                    if (rowWidth + d.width > maxWidth) {
                        addRow(dim, rowWidth, rowHeight)
                        rowWidth = 0
                        rowHeight = 0
                    }
                    if (rowWidth != 0) {
                        rowWidth += hg
                    }
                    rowWidth += d.width
                    rowHeight = max(rowHeight, d.height)
                }
            }
            addRow(dim, rowWidth, rowHeight)
            dim.width += horizontalInsetsAndGap
            dim.height += ins.top + ins.bottom + (vg * 2)
            val scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane::class.java, target)
            if (scrollPane != null && target.isValid) {
                dim.width -= (hg + 1)
            }
            return dim
        }
    }

    private fun addRow(dim: Dimension, rowWidth: Int, rowHeight: Int) {
        dim.width = max(dim.width, rowWidth)
        if (dim.height > 0) {
            dim.height += vgap
        }
        dim.height += rowHeight
    }

}