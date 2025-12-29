package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20

object BackgroundShader {

    private val program = ShaderProgram()

    init {
        program.attach(Shader.create(GL20.GL_VERTEX_SHADER, Resource("assets/vertex.vert")))
        program.attach(Shader.create(GL20.GL_FRAGMENT_SHADER, Resource("assets/background.frag")))
        program.link()
    }

    fun drawBackground(width: Float, height: Float) {

        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        program.bind()

        program.setUniform("u_time", (System.currentTimeMillis() % 100000L) / 1000.0f)
        program.setUniform4f("top_color", 0.61f, 0.35f, 0.71f, 1.0f)
        program.setUniform4f("bottom_color",  0.16f, 0.07f, 0.38f, 1.0f)

        // 振幅
        program.setUniform("wave_amp", 0.018f)
        // 频率
        program.setUniform("wave_size", 4.0f)
        // 速度
        program.setUniform("wave_time_mul", 0.4f)
        // 层数
        program.setUniform("total_phases", 8)

        GL11.glBegin(GL11.GL_QUADS)

        GL11.glTexCoord2f(0f, 0f)
        GL11.glVertex2f(0f, 0f)
        GL11.glTexCoord2f(0f, 1f)
        GL11.glVertex2f(0f, height)
        GL11.glTexCoord2f(1f, 1f)
        GL11.glVertex2f(width, height)
        GL11.glTexCoord2f(1f, 0f)
        GL11.glVertex2f(width, 0f)

        GL11.glEnd()

        program.unbind()

        GL11.glDisable(GL11.GL_BLEND)

    }

}