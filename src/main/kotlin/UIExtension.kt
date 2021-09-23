@file:Suppress("unused")

package com.isyscore.kotlin.swing

import java.awt.GraphicsEnvironment
import java.awt.Toolkit

object UI {
    val density: Int get() = Toolkit.getDefaultToolkit().screenResolution
    val width: Int get() = Toolkit.getDefaultToolkit().screenSize.width
    val height: Int get() = Toolkit.getDefaultToolkit().screenSize.height
    private val rect = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds
    val usableWidth: Int get() = rect.size.width
    val usableHeight: Int get() = rect.size.height
}