package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import java.awt.Color

object GLUtils {

    fun glColor(red: Int, green: Int, blue: Int, alpha: Int) {
        GL11.glColor4f(red / 255f, green / 255f, blue / 255f, alpha / 255f)
    }

    fun glColor(color: Color) {
        glColor(color.red, color.green, color.blue, color.alpha)
    }

    fun glColor(hex: Int) {
        glColor(hex shr 16 and 0xFF, hex shr 8 and 0xFF, hex and 0xFF, hex shr 24 and 0xFF)
    }

}