package com.isyscore.kotlin.swing.component

import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.JPanel

open class ClearPanel: JPanel(null)
open class BorderPanel: JPanel(BorderLayout())
open class VertPanel: JPanel(VertFlowLayout())
open class HorzPanel: JPanel(FlowLayout())
