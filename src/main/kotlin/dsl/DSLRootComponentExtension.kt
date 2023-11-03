@file:Suppress("DuplicatedCode", "unused")

package com.isyscore.kotlin.swing.dsl

import com.isyscore.kotlin.swing.component.*
import com.isyscore.kotlin.swing.inline.newClassInstance
import org.apache.batik.anim.dom.SAXSVGDocumentFactory
import org.apache.batik.swing.JSVGCanvas
import org.apache.batik.util.XMLResourceDescriptor
import java.awt.Component
import java.awt.Image
import java.io.File
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.util.*
import javax.swing.*
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

fun rootLabel(title: String? = null, icon: Icon? = null, horizontalAlignment: Int = JLabel.LEFT, block: JLabel.() -> Unit): JLabel = JLabel(title, icon, horizontalAlignment).apply(block)

fun rootInput(text: String? = null, column: Int = 0, block: KTextField.() -> Unit): KTextField = KTextField(text, column).apply(block)

fun <T> rootCombobox(model: ComboBoxModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JComboBox<T>.() -> Unit): JComboBox<T> = when {
    model != null -> JComboBox(model)
    array != null -> JComboBox(array)
    vector != null -> JComboBox(vector)
    else -> JComboBox()
}.apply(block)

fun rootButton(title: String? = null, icon: Icon? = null, block: JButton.() -> Unit): JButton = JButton(title, icon).apply(block)

fun rootCheckbox(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JCheckBox.() -> Unit): JCheckBox = JCheckBox(title, icon, selected).apply(block)

fun rootRadio(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JRadioButton.() -> Unit): JRadioButton = JRadioButton(title, icon, selected).apply(block)

fun rootToggle(title: String? = null, icon: Icon? = null, selected: Boolean = false, block: JToggleButton.() -> Unit): JToggleButton = JToggleButton(title, icon, selected).apply(block)

fun rootTextArea(text: String? = null, rows: Int = 0, columns: Int = 0, block: KTextArea.() -> Unit): KTextArea = KTextArea(text, rows, columns).apply(block)

fun rootInputPassword(text: String? = null, column: Int = 0, block: KPasswordField.() -> Unit): KPasswordField = KPasswordField(text, column).apply(block)

fun rootTextPane(block: KTextPane.() -> Unit): KTextPane = KTextPane().apply(block)

fun rootEditorPane(contentType: String? = null, text: String? = null, url: URL? = null, block: KEditorPane.() -> Unit): KEditorPane = when {
    url != null -> KEditorPane(url)
    contentType != null -> KEditorPane(contentType, text)
    else -> KEditorPane()
}.apply(block)

fun rootSpinner(model: SpinnerModel? = null, block: JSpinner.() -> Unit): JSpinner = (if (model == null) JSpinner() else JSpinner(model)).apply(block)

fun <T> rootList(model: ListModel<T>? = null, array: Array<T>? = null, vector: Vector<T>? = null, block: JList<T>.() -> Unit): JList<T> = when {
    model != null -> JList(model)
    array != null -> JList(array)
    vector != null -> JList(vector)
    else -> JList()
}.apply(block)

fun rootTable(
    model: TableModel? = null,
    columnModel: TableColumnModel? = null,
    selectionModel: ListSelectionModel? = null,
    rows: Int = -1,
    cols: Int = -1,
    vecRowData: Vector<out Vector<*>>? = null,
    vecColumnNames: Vector<*>? = null,
    arrayRowData: Array<Array<*>>? = null,
    arrayColumnNames: Array<*>? = null,
    block: JTable.() -> Unit
): JTable = when {
    model != null -> JTable(model, columnModel, selectionModel)
    rows != -1 && cols != -1 -> JTable(rows, cols)
    vecRowData != null && vecColumnNames != null -> JTable(vecRowData, vecColumnNames)
    arrayRowData != null && arrayColumnNames != null -> JTable(arrayRowData, arrayColumnNames)
    else -> JTable()
}.apply(block)


fun rootTree(
    model: TreeModel? = null, node: TreeNode? = null, array: Array<*>? = null, vector: Vector<*>? = null, hashtable: Hashtable<*, *>? = null, block: JTree.() -> Unit
): JTree = when {
    model != null -> JTree(model)
    node != null -> JTree(node)
    array != null -> JTree(array)
    vector != null -> JTree(vector)
    hashtable != null -> JTree(hashtable)
    else -> JTree()
}.apply(block)

fun rootProgress(orient: Int = JProgressBar.HORIZONTAL, min: Int = 0, max: Int = 100, block: JProgressBar.() -> Unit): JProgressBar = JProgressBar(orient, min, max).apply(block)

fun rootScrollbar(orientation: Int = JScrollBar.VERTICAL, value: Int = 0, extent: Int = 10, min: Int = 0, max: Int = 100, block: JScrollBar.() -> Unit): JScrollBar = JScrollBar(orientation, value, extent, min, max).apply(block)

fun rootSeparator(orientation: Int = JSeparator.HORIZONTAL, block: JSeparator.() -> Unit): JSeparator = JSeparator(orientation).apply(block)

fun rootSlider(orientation: Int = JSlider.HORIZONTAL, min: Int = 0, max: Int = 100, value: Int = 0, block: JSlider.() -> Unit): JSlider = JSlider(orientation, min, max, value).apply(block)

fun rootBox(axis: Int = 0, block: Box.() -> Unit): Box = Box(axis).apply(block)

fun rootImage(data: ByteArray? = null, img: Image? = null, filename: String? = null, location: URL? = null, block: StretchIcon.() -> Unit): StretchIcon = when {
    data != null -> StretchIcon(imageData = data)
    img != null -> StretchIcon(image = img)
    filename != null -> StretchIcon(filename = filename)
    location != null -> StretchIcon(location = location)
    else -> throw IllegalArgumentException("All image sources are empty.")
}.apply(block)

fun rootSvg(uri: URI? = null, file: File? = null, inputStream: InputStream? = null, block: JSVGCanvas.() -> Unit): JSVGCanvas {
    val canvas = JSVGCanvas()
    when{
        file != null -> canvas.uri = file.toURI().toString()
        else -> {
            val parser = XMLResourceDescriptor.getXMLParserClassName()
            val factory = SAXSVGDocumentFactory(parser)
            canvas.svgDocument = when{
                uri != null -> factory.createSVGDocument(uri.toString())
                inputStream != null -> factory.createSVGDocument("", inputStream)
                else -> throw IllegalArgumentException("All image sources are empty.")
            }
        }
    }
    canvas.apply(block)
    return canvas
}

inline fun <reified T : Component> rootCustom(vararg params: Any, block: T.() -> Unit): T = newClassInstance<T>(*params).apply(block)

fun <T : Component> rootComp(comp: T, block: T.() -> Unit): T = comp.apply(block)


