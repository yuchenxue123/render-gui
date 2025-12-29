package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import java.awt.Color

object RenderEngine {

    fun drawRect(x: Float, y: Float, width: Float, height: Float, color: Color) {
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)

        GLUtils.glColor(color)

        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex2f(x + width, y)
        GL11.glVertex2f(x, y)
        GL11.glVertex2f(x, y + height)
        GL11.glVertex2f(x + width, y + height)
        GL11.glEnd()

        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
    }
}