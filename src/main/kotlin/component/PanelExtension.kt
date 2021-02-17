package com.isyscore.kotlin.swing.component

import java.awt.BorderLayout
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.JPanel

class ClearPanel: JPanel(null) {
    init {
        background = Color.WHITE
    }
}

class BorderPanel: JPanel(BorderLayout()) {
    init {
        background = Color.WHITE
    }
}

class VertPanel: JPanel(VertFlowLayout()) {
    init {
        background = Color.WHITE
    }
}

class HorzPanel: JPanel(FlowLayout()) {
    init {
        background = Color.WHITE
    }
}
