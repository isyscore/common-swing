package com.isyscore.kotlin.swing.component

import java.awt.Component
import java.awt.Container
import java.awt.Graphics
import java.awt.Image
import java.net.URL
import javax.swing.ImageIcon

open class StretchIcon: ImageIcon {

    protected var proportionate = true

    constructor(imageData: ByteArray): super(imageData)
    constructor(imageData: ByteArray, proportionate: Boolean): super(imageData) {
        this.proportionate = proportionate
    }
    constructor(imageData: ByteArray, description: String): super(imageData, description)
    constructor(imageData: ByteArray, description: String, proportionate: Boolean): super(imageData, description) {
        this.proportionate = proportionate
    }
    constructor(image: Image): super(image)
    constructor(image: Image, proportionate: Boolean): super(image) {
        this.proportionate = proportionate
    }
    constructor(image: Image, description: String): super(image, description)
    constructor(image: Image, description: String, proportionate: Boolean): super(image, description) {
        this.proportionate = proportionate
    }
    constructor(filename: String): super(filename)
    constructor(filename: String, proportionate: Boolean): super(filename) {
        this.proportionate = proportionate
    }
    constructor(filename: String, description: String): super(filename, description)
    constructor(filename: String, description: String, proportionate: Boolean): super(filename, description) {
        this.proportionate = proportionate
    }
    constructor(location: URL): super(location)
    constructor(location: URL, proportionate: Boolean): super(location) {
        this.proportionate = proportionate
    }
    constructor(location: URL, description: String): super(location, description)
    constructor(location: URL, description: String, proportionate: Boolean): super(location, description) {
        this.proportionate = proportionate
    }

    @Synchronized
    override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
        var x = x
        var y = y
        val img = image ?: return
        val ins = (c as Container).insets
        x = ins.left
        y = ins.top
        var w = c.width - x - ins.right
        var h = c.height - y - ins.bottom
        if (proportionate) {
            var iw = img.getWidth(c)
            var ih = img.getHeight(c)
            if (iw * h < ih * w) {
                iw = (h * iw) / ih
                x += (w - iw) / 2
                w = iw
            } else {
                ih = (w * ih) / iw
                y += (h - ih) / 2
                h = ih
            }
        }
        val io = imageObserver
        g.drawImage(img, x, y, w, h, io ?: c)
    }

    override fun getIconWidth(): Int = 0
    override fun getIconHeight(): Int = 0
}