package com.isyscore.kotlin.swing.component

import com.isyscore.kotlin.common.isMac
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.text.JTextComponent

class EditPopupMenu(comp: JTextComponent): JPopupMenu() {
    init {
        add(JMenuItem("Cut").apply { addActionListener { comp.cut() } })
        add(JMenuItem("Copy").apply { addActionListener { comp.copy() } })
        add(JMenuItem("Paste").apply { addActionListener { comp.paste() } })
        addSeparator()
        add(JMenuItem("Select All").apply { addActionListener { comp.selectAll() } })
    }
}

class EditKeyListener(private val comp: JTextComponent): KeyListener {
    override fun keyTyped(e: KeyEvent) {}
    override fun keyPressed(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}

}

class EditMouseListener(private val comp: JTextComponent, private val popup: JPopupMenu): MouseListener {
    override fun mouseClicked(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {}
    override fun mouseReleased(e: MouseEvent) {
        if (e.button == MouseEvent.BUTTON3) {
            popup.show(comp, e.x, e.y)
        }
    }
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}
}