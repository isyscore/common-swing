package com.isyscore.kotlin.swing.component

import java.awt.*
import java.awt.font.FontRenderContext
import java.util.*
import javax.swing.JWindow
import kotlin.concurrent.timerTask

fun toast(parent: Window, message: String, delay: Int = 1500, type: Int = Toast.MESSAGE) = Toast(parent, message, delay, type).showToast()

@JvmName("windowToast")
fun Window.toast(message: String, delay: Int = 1500, type: Int = Toast.MESSAGE) = Toast(this, message, delay, type).showToast()

private class Toast : JWindow {

    companion object {
        const val MESSAGE = 0
        const val SUCCESS = 1
        const val ERROR = 2
    }

    private var message = ""
    private val ins = Insets(12, 24, 12, 24)
    private var period = 1500

    private lateinit var backgroundColor: Color
    private lateinit var foregroundColor: Color

    constructor(parent: Window, message: String, period: Int = 1500) : this(parent, message, period, 0)

    constructor(parent: Window, message: String, period: Int, type: Int) : super(parent) {
        this.message = message
        this.period = period
        size = getStringSize(message)
        setLocationRelativeTo(parent)
        installTheme(type)
    }

    override fun paint(g: Graphics) {
        (g as Graphics2D).apply {
            val oldComposite = composite
            setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            val fm = fontMetrics
            color = backgroundColor
            fillRoundRect(0, 0, width, height, 12, 12)
            color = foregroundColor
            drawString(message, ins.left, fm.ascent + ins.top)
            composite = oldComposite
        }
    }

    fun showToast() {
        this.isVisible = true
        revalidate()
        repaint(0, 0, size.width, size.height)
        Timer().schedule(timerTask { isVisible = false }, period.toLong())
    }

    fun setMessage(message: String) {
        this.message = message
        val size = getStringSize(message)
        setSize(size)
        revalidate()
        repaint(0, 0, size.width, size.height)
        if (!isVisible) {
            showToast()
        }
    }

    private fun installTheme(type: Int) = when (type) {
        MESSAGE -> {
            backgroundColor = Color(0x515151)
            foregroundColor = Color.WHITE
        }
        SUCCESS -> {
            backgroundColor = Color(223, 240, 216)
            foregroundColor = Color(49, 112, 143)
        }
        ERROR -> {
            backgroundColor = Color(242, 222, 222)
            foregroundColor = Color(221, 17, 68)
        }
        else -> {
            backgroundColor = Color(0x515151)
            foregroundColor = Color.WHITE
        }
    }

    private fun getStringSize(text: String): Dimension {
        val renderContext = FontRenderContext(null, true, false)
        val bounds = font.getStringBounds(text, renderContext)
        val width = bounds.width.toInt() + 2 * ins.left
        val height = bounds.height.toInt() + ins.top * 2
        return Dimension(width, height)
    }
}