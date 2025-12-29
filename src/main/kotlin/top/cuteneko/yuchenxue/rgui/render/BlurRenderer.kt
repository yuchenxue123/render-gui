package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import top.cuteneko.yuchenxue.rgui.MinecraftShortcut
import java.awt.Color

object BlurRenderer : MinecraftShortcut {

    private lateinit var framebuffer: FrameBufferObject

    private val program: ShaderProgram = ShaderProgram()

    private var initialized = false

    fun drawBlurRect(x: Float, y: Float, width: Float, height: Float, color: Color, strength: Float = 1f) {
//        RenderEngine.drawRect(x, y, width, height, color)

        if (!initialized) {
            framebuffer = FrameBufferObject(mc.displayWidth, mc.displayHeight)

            program.attach(Shader.create(GL20.GL_VERTEX_SHADER, Resource("assets/vertex.vert")))
            program.attach(Shader.create(GL20.GL_FRAGMENT_SHADER, Resource("assets/blur.frag")))
            program.link()

            initialized = true
        }

        if (framebuffer.width != mc.displayWidth || framebuffer.height != mc.displayWidth) {
            framebuffer.delete()
            framebuffer = FrameBufferObject(mc.displayWidth, mc.displayHeight)
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer.texture)
        GL11.glCopyTexImage2D(
            GL11.GL_TEXTURE_2D,
            0,
            GL11.GL_RGB,
            0, 0,
            framebuffer.width, framebuffer.height,
            0
        )
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)


        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)

        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_DEPTH_TEST)

        program.bind()

        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer.texture)

        program.setUniform("u_texture", 0)
        program.setUniform2f("u_resolution", mc.displayWidth.toFloat(), mc.displayHeight.toFloat())

        val red = color.red / 255f
        val green = color.green / 255f
        val blue = color.blue / 255f
        val alpha = color.alpha / 255f

        program.setUniform4f("u_color", red, green, blue, alpha)

        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex2f(x, y + height)
        GL11.glVertex2f(x + width, y + height)
        GL11.glVertex2f(x + width, y)
        GL11.glVertex2f(x, y)
        GL11.glEnd()

        program.unbind()
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
        GL11.glPopAttrib()

    }

}