package com.isyscore.kotlin.swing.component

import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.JPanel

open class ClearPanel: JPanel(null) {
    init {
        background = Color.WHITE
    }
}

open class BorderPanel: JPanel(BorderLayout()) {
    init {
        background = Color.WHITE
    }
}

open class VertPanel: JPanel(VertFlowLayout()) {
    init {
        background = Color.WHITE
    }
}

open class HorzPanel: JPanel(FlowLayout()) {
    init {
        background = Color.WHITE
    }
}
