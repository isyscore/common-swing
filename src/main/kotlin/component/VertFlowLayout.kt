@file:Suppress("DuplicatedCode", "unused", "MemberVisibilityCanBePrivate")

package com.isyscore.kotlin.swing.component

import java.awt.Container
import java.awt.Dimension
import java.awt.FlowLayout
import kotlin.math.max

class VertFlowLayout : FlowLayout {

    companion object {
        /* Specify alignment top */
        const val TOP = 0

        /* Specify a middle alignment */
        const val MIDDLE = 1

        /* Specify the alignment to be bottom */
        const val BOTTOM = 2
    }

    var hfill = false
    var vfill = false

    constructor() : this(TOP, 4, 4, true, false)
    constructor(hfill: Boolean, vfill: Boolean) : this(TOP, 4, 4, hfill, vfill)
    constructor(align: Int) : this(align, 4, 4, true, false)
    constructor(align: Int, hfill: Boolean, vfill: Boolean) : this(align, 4, 4, hfill, vfill)
    constructor(align: Int, hgap: Int, vgap: Int, hfill: Boolean, vfill: Boolean) {
        this.alignment = align
        this.hgap = hgap
        this.vgap = vgap
        this.hfill = hfill
        this.vfill = vfill
    }

    override fun preferredLayoutSize(target: Container): Dimension {
        val tarSiz = Dimension(0, 0)
        for (i in 0 until target.componentCount) {
            val m = target.getComponent(i)
            if (m.isVisible) {
                val d = m.preferredSize
                tarSiz.width = max(tarSiz.width, d.width)
                if (i > 0) tarSiz.height += vgap
                tarSiz.height += d.height
            }
        }
        val insets = target.insets
        tarSiz.width += insets.left + insets.right + hgap * 2
        tarSiz.height += insets.top + insets.bottom + vgap * 2
        return tarSiz
    }

    override fun minimumLayoutSize(target: Container): Dimension {
        val tarSiz = Dimension(0, 0)
        for (i in 0 until target.componentCount) {
            val m = target.getComponent(i)
            if (m.isVisible) {
                val d = m.minimumSize
                tarSiz.width = max(tarSiz.width, d.width)
                if (i > 0) tarSiz.height += vgap
                tarSiz.height += d.height
            }
        }
        val insets = target.insets
        tarSiz.width += insets.left + insets.right + hgap * 2
        tarSiz.height += insets.top + insets.bottom + vgap * 2
        return tarSiz
    }


    fun setVerticalFill(vfill: Boolean) {
        this.vfill = vfill
    }

    fun getVerticalFill(): Boolean = vfill

    fun setHorizontalFill(hfill: Boolean) {
        this.hfill = hfill
    }

    fun getHorizontalFill(): Boolean = hfill

    private fun placeThem(target: Container, x: Int, y: Int, width: Int, height: Int, first: Int, last: Int) {
        @Suppress("NAME_SHADOWING") var y = y
        val align = alignment
        if (align == MIDDLE) y += height / 2
        if (align == BOTTOM) y += height
        for (i in first until last) {
            val m = target.getComponent(i)
            val md = m.size
            if (m.isVisible) {
                val px = x + (width - md.width) / 2
                m.setLocation(px, y)
                y += vgap + md.height
            }
        }
    }

    override fun layoutContainer(target: Container) {
        val insets = target.insets
        val maxHeight = target.size.height - (insets.top + insets.bottom + vgap * 2)
        val maxWidth = target.size.width - (insets.left + insets.right + hgap * 2)
        val numComp = target.componentCount
        var x = insets.left + hgap
        var y = 0
        var colw = 0
        var start = 0
        for (i in 0 until numComp) {
            val m = target.getComponent(i)
            if (m.isVisible) {
                val d = m.preferredSize
                if (this.vfill && i == (numComp - 1)) d.height = max(maxHeight - y, m.preferredSize.height)
                if (this.hfill) {
                    m.setSize(maxWidth, d.height)
                    d.width = maxWidth
                } else {
                    m.setSize(d.width, d.height)
                }
                if (y + d.height > maxHeight) {
                    placeThem(target, x, insets.top + vgap, colw, maxHeight - y, start, i)
                    y = d.height
                    x += hgap + colw
                    colw = d.width
                    start = i
                } else {
                    if (y > 0) y += vgap
                    y += d.height
                    colw = max(colw, d.width)
                }
            }
        }
        placeThem(target, x, insets.top + vgap, colw, maxHeight - y, start, numComp)
    }
}