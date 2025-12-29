package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import java.awt.Color

object RoundRectangleShader {

    val program = ShaderProgram()

    init {
        program.attach(Shader.create(GL20.GL_VERTEX_SHADER, Resource("assets/vertex.vert")))
        program.attach(Shader.create(GL20.GL_FRAGMENT_SHADER, Resource("assets/round_rect.frag")))
        program.link()
    }

    fun drawRoundRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {

        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        program.bind()

        program.setUniform2f("u_size", width, height)

        val red = color.red / 255f
        val green = color.green / 255f
        val blue = color.blue / 255f
        val alpha = color.alpha / 255f

        program.setUniform4f("u_color", red, green, blue, alpha)

        program.setUniform("u_radius", radius)

        GL11.glBegin(GL11.GL_QUADS)

        GL11.glTexCoord2f(0f, 0f)
        GL11.glVertex2f(x, y)
        GL11.glTexCoord2f(0f, 1f)
        GL11.glVertex2f(x, y + height)
        GL11.glTexCoord2f(1f, 1f)
        GL11.glVertex2f(x + width, y + height)
        GL11.glTexCoord2f(1f, 0f)
        GL11.glVertex2f(x + width, y)

        GL11.glEnd()

        program.unbind()

        GL11.glDisable(GL11.GL_BLEND)

    }
}