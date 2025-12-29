package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20

class Shader private constructor(
    type: Int
) {
    private val id: Int = GL20.glCreateShader(type)

    companion object {
        fun create(type: Int, source: String): Shader {
            val shader = Shader(type)
            shader.compile(source)
            return shader
        }

        fun create(type: Int, resource: Resource): Shader {
            return create(type, resource.content())
        }
    }

    fun compile(source: String) {
        GL20.glShaderSource(id, source)
        GL20.glCompileShader(id)

        if (GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw ShaderCompileException(GL20.glGetShaderInfoLog(id, 1024))
        }
    }

    fun delete() {
        GL20.glDeleteShader(id)
    }

    fun id() = id


    private class ShaderCompileException(msg: String) : RuntimeException(msg)
}