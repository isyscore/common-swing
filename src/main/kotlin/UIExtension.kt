@file:Suppress("unused")

package com.isyscore.kotlin.swing

import com.apple.eawt.AboutHandler
import com.apple.eawt.AppEvent
import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.themes.FlatMacDarkLaf
import com.formdev.flatlaf.themes.FlatMacLightLaf
import com.formdev.flatlaf.util.SystemInfo
import com.isyscore.kotlin.common.isMac
import com.isyscore.kotlin.common.isUnix
import com.isyscore.kotlin.common.isWindows
import com.jtattoo.plaf.acryl.AcrylLookAndFeel
import com.jtattoo.plaf.aero.AeroLookAndFeel
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
import com.jtattoo.plaf.fast.FastLookAndFeel
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel
import com.jtattoo.plaf.hifi.HiFiLookAndFeel
import com.jtattoo.plaf.luna.LunaLookAndFeel
import com.jtattoo.plaf.mcwin.McWinLookAndFeel
import com.jtattoo.plaf.mint.MintLookAndFeel
import com.jtattoo.plaf.noire.NoireLookAndFeel
import com.jtattoo.plaf.smart.SmartLookAndFeel
import com.jtattoo.plaf.texture.TextureLookAndFeel
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel
import com.sun.java.swing.plaf.motif.MotifLookAndFeel
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.Toolkit
import java.awt.Window
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.plaf.metal.MetalLookAndFeel

enum class UIStyle {
    Light, Dark, Darcula, Intellij, Metal, Motif, Windows, WindowsClassic, GTK,
    Acryl, Aero, Aluminium, Bernstein, Fast, HiFi, McWin, Mint, Noire, Smart, Luna, Texture, Graphite
}

object UI {
    val density: Int get() = Toolkit.getDefaultToolkit().screenResolution
    val width: Int get() = Toolkit.getDefaultToolkit().screenSize.width
    val height: Int get() = Toolkit.getDefaultToolkit().screenSize.height
    private val rect = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds
    val usableWidth: Int get() = rect.size.width
    val usableHeight: Int get() = rect.size.height

    fun lookAndFeel(style: UIStyle, decorated: Boolean = true) {
        JFrame.setDefaultLookAndFeelDecorated(decorated)
        JDialog.setDefaultLookAndFeelDecorated(decorated)
        UIManager.setLookAndFeel(when(style) {
            UIStyle.Light -> if (isMac) FlatMacLightLaf() else FlatLightLaf()
            UIStyle.Dark -> if (isMac) FlatMacDarkLaf() else FlatMacDarkLaf()
            UIStyle.Darcula -> FlatDarculaLaf()
            UIStyle.Intellij -> FlatIntelliJLaf()
            UIStyle.Metal -> MetalLookAndFeel()
            UIStyle.Motif -> MotifLookAndFeel()
            UIStyle.Windows -> if (isWindows) WindowsLookAndFeel() else FlatLightLaf()
            UIStyle.WindowsClassic -> if (isWindows) WindowsClassicLookAndFeel() else FlatLightLaf()
            UIStyle.GTK -> if (isUnix) GTKLookAndFeel() else FlatLightLaf()
            UIStyle.Acryl -> AcrylLookAndFeel()
            UIStyle.Aero -> AeroLookAndFeel()
            UIStyle.Aluminium -> AluminiumLookAndFeel()
            UIStyle.Bernstein -> BernsteinLookAndFeel()
            UIStyle.Fast -> FastLookAndFeel()
            UIStyle.HiFi -> HiFiLookAndFeel()
            UIStyle.McWin -> McWinLookAndFeel()
            UIStyle.Mint -> MintLookAndFeel()
            UIStyle.Noire -> NoireLookAndFeel()
            UIStyle.Smart -> SmartLookAndFeel()
            UIStyle.Luna -> LunaLookAndFeel()
            UIStyle.Texture -> TextureLookAndFeel()
            UIStyle.Graphite -> GraphiteLookAndFeel()
        })
    }

    object Mac {

        enum class MacAppearance {
            SYSTEM, LIGHT, DARK
        }

        /**
         * 是否使用全局的菜单栏（在程序初始化时使用）
         */
        fun useScreenMenuBar(use: Boolean) {
            if (isMac) {
                System.setProperty("apple.laf.useScreenMenuBar", if (use) "true" else "false")
            }
        }

        /**
         * 设置应用的标题栏（在程序初始化时使用）
         */
        fun setApplicationName(name: String) {
            if (isMac) {
                System.setProperty("apple.awt.application.name", name)
            }
        }

        /**
         * 设置应用的样式，跟随系统/亮色/暗色（在程序初始化时使用）
         */
        fun setAppearance(appearance: MacAppearance) {
            if (isMac) {
                System.setProperty("apple.awt.application.appearance", when(appearance) {
                    MacAppearance.SYSTEM -> "system"
                    MacAppearance.LIGHT -> "light"
                    MacAppearance.DARK -> "dark"
                })
            }
        }

        /**
         * 为 Frame 设置全屏内容和标题栏透明
         */
        fun JFrame.frameAppearance(fullWindow: Boolean, transparentTitleBar: Boolean) {
            if (isMac && SystemInfo.isMacFullWindowContentSupported) {
                rootPane.putClientProperty("apple.awt.fullWindowContent", fullWindow)
                rootPane.putClientProperty("apple.awt.transparentTitleBar", transparentTitleBar)
            }
        }

        /**
         * 为 Dialog 设置全屏内容和标题栏透明
         */
        fun JDialog.dialogAppearance(fullWindow: Boolean, transparentTitleBar: Boolean) {
            if (isMac && SystemInfo.isMacFullWindowContentSupported) {
                rootPane.putClientProperty("apple.awt.fullWindowContent", fullWindow)
                rootPane.putClientProperty("apple.awt.transparentTitleBar", transparentTitleBar)
            }
        }

        private class AboutAdapter(private val owner: Window, val handler: (Window) -> Unit): AboutHandler {
            override fun handleAbout(e: AppEvent.AboutEvent?) {
                handler(owner)
            }
        }

        /**
         * 替换 Mac 的关于对话框
         */
        fun JFrame.hookAbout(handler: (Window) -> Unit) {
            if (isMac) {
                val cApplication = Class.forName("com.apple.eawt.Application")
                val mGetApp = cApplication.getDeclaredMethod("getApplication")
                val oApp = mGetApp.invoke(null)
                val mSetAboutHandler = cApplication.getDeclaredMethod("setAboutHandler", AboutHandler::class.java)
                mSetAboutHandler.invoke(oApp, AboutAdapter(this, handler))
            }
        }

        /**
         * 替换 Mac 的关于对话框
         */
        fun JDialog.hookAbout(handler: (Window) -> Unit) {
            if (isMac) {
                val cApplication = Class.forName("com.apple.eawt.Application")
                val mGetApp = cApplication.getDeclaredMethod("getApplication")
                val oApp = mGetApp.invoke(null)
                val mSetAboutHandler = cApplication.getDeclaredMethod("setAboutHandler", AboutHandler::class.java)
                mSetAboutHandler.invoke(oApp, AboutAdapter(this, handler))
            }
        }

        /**
         * 设置 Mac 的 Docker 图标
         */
        fun setDockImage(image: Image) {
            if (isMac) {
                val cApplication = Class.forName("com.apple.eawt.Application")
                val mGetApp = cApplication.getDeclaredMethod("getApplication")
                val oApp = mGetApp.invoke(null)
                val mSetDockImage = cApplication.getDeclaredMethod("setDockIconImage", Image::class.java)
                mSetDockImage.invoke(oApp, image)
            }
        }

    }

}