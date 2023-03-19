package com.isyscore.kotlin.swing.component

import com.isyscore.kotlin.swing.dsl.ContextDsl
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.IProgressHandler
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.CefClient
import org.cef.CefSettings
import org.cef.browser.CefBrowser
import org.cef.browser.CefMessageRouter
import org.cef.handler.*
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel

class Browser : JPanel(BorderLayout()) {

    class BrowserConfig {
        internal val builder = CefAppBuilder()
        internal var _onProgress: IProgressHandler? = null
        internal var _onExit: ActionListener? = null
        internal var _onLifeSpan: CefLifeSpanHandler? = null
        internal var _onDialog: CefDialogHandler? = null
        internal var _onDisplay: CefDisplayHandler? = null
        internal var _onContextMenu: CefContextMenuHandler? = null
        internal var _onDownload: CefDownloadHandler? = null
        internal var _onDrag: CefDragHandler? = null
        internal var _onFocus: CefFocusHandler? = null
        internal var _onJSDialog: CefJSDialogHandler? = null
        internal var _onKeyboard: CefKeyboardHandler? = null
        internal var _onLoad: CefLoadHandler? = null
        internal var _onPrint: CefPrintHandler? = null
        internal var _onRequest: CefRequestHandler? = null

        var url = "about:blank"
        var args: Array<String>? = null
        var isTransparent = false
        val settings: CefSettings get() = builder.cefSettings

        fun addRequestHandler(listener: CefRequestHandler) {
            _onRequest = listener
        }

        fun removeRequestHandler() {
            _onRequest = null
        }

        fun addPrintHandler(listener: CefPrintHandler) {
            _onPrint = listener
        }

        fun removePrintHandler() {
            _onPrint = null
        }

        fun addLoadHandler(listener: CefLoadHandler) {
            _onLoad = listener
        }

        fun removeLoadHandler() {
            _onLoad = null
        }

        fun addKeyboardHandler(listener: CefKeyboardHandler) {
            _onKeyboard = listener
        }

        fun removeKeyboardHandler() {
            _onKeyboard = null
        }

        fun addJsDialogHandler(listener: CefJSDialogHandler) {
            _onJSDialog = listener
        }

        fun removeJsDialogHandler() {
            _onJSDialog = null
        }

        fun addFocusHandler(listener: CefFocusHandler) {
            _onFocus = listener
        }

        fun removeFocusHandler() {
            _onFocus = null
        }

        fun addDragHandler(listener: CefDragHandler) {
            _onDrag = listener
        }

        fun removeDragHandler() {
            _onDrag = null
        }

        fun addDownloadHandler(listener: CefDownloadHandler) {
            _onDownload = listener
        }

        fun removeDownloadHandler() {
            _onDownload = null
        }

        fun addContentMenuHandler(listener: CefContextMenuHandler) {
            _onContextMenu = listener
        }

        fun removeContentMenuHandler() {
            _onContextMenu = null
        }

        fun addDisplayHandler(listener: CefDisplayHandler) {
            _onDisplay = listener
        }

        fun removeDisplayHandler() {
            _onDisplay = null
        }

        fun addDialogHandler(listener: CefDialogHandler) {
            _onDialog = listener
        }

        fun removeDialogHandler() {
            _onDialog = null
        }

        fun addProgressHandler(listener: IProgressHandler) {
            _onProgress = listener
        }

        fun removeProgressHandler() {
            _onProgress = null
        }

        fun addExitListener(listener: ActionListener) {
            _onExit = listener
        }

        fun removeExitListener() {
            _onExit = null
        }

        fun addLifeSpanHandler(listener: CefLifeSpanHandler) {
            _onLifeSpan = listener
        }

        fun removeLifeSpanHandler() {
            _onLifeSpan = null
        }

    }

    private val _cfg = BrowserConfig()

    private lateinit var cefApp: CefApp
    private lateinit var cefClient: CefClient
    private lateinit var cefBrowser: CefBrowser

    val client: CefClient get() = cefClient
    val app: CefApp get() = cefApp
    val browser: CefBrowser get() = cefBrowser

    fun config(cfg: BrowserConfig.() -> Unit) {
        cfg(_cfg)
    }

    fun render() {
        _cfg.builder.setAppHandler(object : MavenCefAppHandlerAdapter() {
            override fun stateHasChanged(state: CefApp.CefAppState?) {
                if (state == CefApp.CefAppState.TERMINATED) {
                    _cfg._onExit?.actionPerformed(ActionEvent(cefBrowser, 0, ""))
                }
            }
        })
        if (_cfg._onProgress != null) _cfg.builder.setProgressHandler(_cfg._onProgress)
        cefApp = _cfg.builder.build()
        cefClient = cefApp.createClient()

        cefClient.addDialogHandler(_cfg._onDialog)
        cefClient.addDisplayHandler(_cfg._onDisplay)
        cefClient.addContextMenuHandler(_cfg._onContextMenu)
        cefClient.addDownloadHandler(_cfg._onDownload)
        cefClient.addDragHandler(_cfg._onDrag)
        cefClient.addFocusHandler(_cfg._onFocus)
        cefClient.addJSDialogHandler(_cfg._onJSDialog)
        cefClient.addKeyboardHandler(_cfg._onKeyboard)
        cefClient.addLoadHandler(_cfg._onLoad)
        cefClient.addPrintHandler(_cfg._onPrint)
        cefClient.addRequestHandler(_cfg._onRequest)
        cefClient.addLifeSpanHandler(_cfg._onLifeSpan)

        val msgRouter = CefMessageRouter.create()
        cefClient.addMessageRouter(msgRouter)
        cefBrowser = cefClient.createBrowser(_cfg.url, _cfg.builder.cefSettings.windowless_rendering_enabled, _cfg.isTransparent)

        val ui = cefBrowser.uiComponent
        add(ui, BorderLayout.CENTER)
    }
}