@file:Suppress("unused")

package com.isyscore.kotlin.swing

import javax.swing.SwingUtilities

fun runOnMainThread(runnable: () -> Unit) {
    SwingUtilities.invokeLater(runnable)
}

fun runOnMainThread(runnable: Runnable) {
    SwingUtilities.invokeLater(runnable)
}
