package com.isyscore.kotlin.swing.component

import java.net.URL
import javax.swing.*

class KTextField(title: String? = null, column: Int = 0) : JTextField(title, column) {
    init {
        val popup = EditPopupMenu(this)
        addKeyListener(EditKeyListener(this))
        addMouseListener(EditMouseListener(this, popup))
    }
}

class KTextArea(text: String? = null, rows: Int = 0, columns: Int = 0): JTextArea(text, rows, columns) {
    init {
        val popup = EditPopupMenu(this)
        addKeyListener(EditKeyListener(this))
        addMouseListener(EditMouseListener(this, popup))
    }
}

class KPasswordField(text: String? = null, column: Int = 0): JPasswordField(text, column) {
    init {
        val popup = EditPopupMenu(this)
        addKeyListener(EditKeyListener(this))
        addMouseListener(EditMouseListener(this, popup))
    }
}

class KTextPane: JTextPane() {
    init {
        val popup = EditPopupMenu(this)
        addKeyListener(EditKeyListener(this))
        addMouseListener(EditMouseListener(this, popup))
    }
}

class KEditorPane: JEditorPane {
    constructor(): super()
    constructor(url: URL?): super(url)
    constructor(contentType: String?, text: String?): super(contentType, text)
    init {
        val popup = EditPopupMenu(this)
        addKeyListener(EditKeyListener(this))
        addMouseListener(EditMouseListener(this, popup))
    }
}
