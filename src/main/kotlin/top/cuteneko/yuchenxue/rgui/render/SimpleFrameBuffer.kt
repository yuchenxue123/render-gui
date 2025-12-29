package top.cuteneko.yuchenxue.rgui.render

import cute.neko.event.EventManager
import net.minecraft.ScaledResolution
import net.minecraft.Tessellator
import org.lwjgl.opengl.GL11
import top.cuteneko.yuchenxue.rgui.MinecraftShortcut
import java.awt.Color

object SimpleFrameBuffer : MinecraftShortcut {

    private lateinit var framebuffer: FrameBufferObject

    private var initialized = false

    fun render(width: Int, height: Int) {

        if (!initialized) {
            framebuffer = FrameBufferObject(width, height)
            initialized = true
        }

        if (framebuffer.width != width || framebuffer.height != height) {
            framebuffer.delete()
            framebuffer = FrameBufferObject(width, height)
        }

        // 备份状态
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glPushMatrix()

        framebuffer.bind()

        GL11.glViewport(0, 0, framebuffer.width, framebuffer.height)

        // 清除背景
        GL11.glClearColor(0f, 0f, 0f, 0f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

        val sr = ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight)

        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()

        GL11.glOrtho(0.0, sr.scaledWidth_double, sr.scaledHeight_double, 0.0, 1000.0, 3000.0)

        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glLoadIdentity()
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f)

        // -----绘制-----
        EventManager.callEvent(RenderEvent())

        BlurRenderer.drawBlurRect(
            0f,
            0f,
            200f,
            100f,
            Color(255, 100, 255, 80)
        )

        RoundRectangleShader.drawRoundRectangle(
            250f,
            0f,
            200f,
            100f,
            24f,
            Color(255, 255, 255, 180)
        )

        EllipseShader.drawEllipse(
            500f,
            0f,
            200f,
            100f,
            Color(255, 100, 255, 255)
        )

        // -------------

        framebuffer.unbind()

        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight)

        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glOrtho(0.0, sr.scaledWidth_double, sr.scaledHeight_double, 0.0, 1000.0, 3000.0)
        GL11.glMatrixMode(GL11.GL_MODELVIEW)

        // 恢复状态
        GL11.glPopMatrix()
        GL11.glPopAttrib()

        drawToScreen()
    }

    private fun drawToScreen() {
        val sr = ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight)
        val width = sr.scaledWidth_double
        val height = sr.scaledHeight_double

        GL11.glEnable(GL11.GL_BLEND)

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer.texture)

        val buffer = Tessellator.instance
        buffer.startDrawingQuads()

        val uMin = 0.0
        val uMax = 1.0

        val vMin = 0.0
        val vMax = 1.0

        buffer.addVertexWithUV(0.0, height, 0.0, uMin, vMin)
        buffer.addVertexWithUV(width, height, 0.0, uMax, vMin)
        buffer.addVertexWithUV(width, 0.0, 0.0, uMax, vMax)
        buffer.addVertexWithUV(0.0, 0.0, 0.0, uMin, vMax)

        buffer.draw()

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
    }

}