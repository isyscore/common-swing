@file:Suppress("unused", "UnusedReceiverParameter", "RemoveRedundantQualifierName")

package com.isyscore.kotlin.swing

import java.awt.Component
import java.io.File
import javax.swing.Icon
import javax.swing.JFileChooser
import javax.swing.JOptionPane
import javax.swing.JOptionPane.*
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileSystemView

fun Component.showFileDialog(filters: Array<FileFilter>, isSave: Boolean = false, defaultDir: String? = null, callback: (File) -> Unit) = JFileChooser().apply {
    currentDirectory = if (defaultDir == null) FileSystemView.getFileSystemView().homeDirectory else File(defaultDir)
    isMultiSelectionEnabled = false
    filters.forEach { addChoosableFileFilter(it) }
    val retVal = if (isSave) showSaveDialog(this) else showOpenDialog(this)
    if (retVal == JFileChooser.APPROVE_OPTION) {
        val f = selectedFile
        if (f != null) {
            callback(f)
        }
    }
}

fun Component.showDirectoryDialog(defaultDir: String? = null, callback: (File) -> Unit) = JFileChooser().apply {
    currentDirectory = if (defaultDir == null) FileSystemView.getFileSystemView().homeDirectory else File(defaultDir)
    fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    val retVal = showOpenDialog(this)
    if (retVal == JFileChooser.APPROVE_OPTION) {
        val f = selectedFile
        if (f != null) {
            callback(f)
        }
    }
}

fun Component.showInputDialog(title: String, message: String, callback: (String) -> Unit) {
    val value = JOptionPane.showInputDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE)
    if (value != null) {
        callback(value)
    }
}

fun Component.errorMessageBox(title: String, msg: String) {
    JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE)
}

fun Component.messageBox(title: String, msg: String) {
    JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE)
}

fun Component.questionMessageBox(title: String, msg: String) {
    JOptionPane.showMessageDialog(this, msg, title, JOptionPane.QUESTION_MESSAGE)
}

fun Component.warningMessageBox(title: String, msg: String) {
    JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE)
}

/**
 *  @param optionType YES_NO_OPTION, YES_NO_CANCEL_OPTION, or OK_CANCEL_OPTION
 *  @param messageType ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE
 */
fun Component.confirmDialog(title: String, msg: String, optionType: Int = OK_CANCEL_OPTION, messageType: Int = PLAIN_MESSAGE, icon: Icon? = null): Int =
    JOptionPane.showConfirmDialog(this, msg, title, optionType, messageType, icon)

/**
 * @param optionType DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION, or OK_CANCEL_OPTION
 * @param messageType ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE
 */
fun Component.optionDialog(title: String, msg: String, optionType: Int = DEFAULT_OPTION, messageType: Int = PLAIN_MESSAGE, icon: Icon? = null, options: Array<String> = arrayOf(), defVal: String = ""): Int =
    JOptionPane.showOptionDialog(this, msg, title, optionType, messageType, icon, options, defVal)