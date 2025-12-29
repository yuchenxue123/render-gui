package top.cuteneko.yuchenxue.rgui.render

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20

class ShaderProgram {

    private val id: Int = GL20.glCreateProgram()

    fun attach(shader: Shader) {
        GL20.glAttachShader(id, shader.id())
    }

    fun link() {
        GL20.glLinkProgram(id)

        if (GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            throw ShaderLinkException(GL20.glGetProgramInfoLog(id, 1024))
        }
    }

    fun bind() {
        GL11.glPushMatrix()
        GL20.glUseProgram(id)
    }

    fun unbind() {
        GL20.glUseProgram(0)
        GL11.glPopMatrix()
    }

    fun getUniformLocation(name: String): Int {
        val location = GL20.glGetUniformLocation(id, name)
        if (location == -1) {
            throw RuntimeException("Uniform '$name' not found in program $id")
        }
        return location
    }

    fun setUniform(name: String, value: Int) {
        GL20.glUniform1i(getUniformLocation(name), value)
    }

    fun setUniform(name: String, value: Float) {
        GL20.glUniform1f(getUniformLocation(name), value)
    }

    fun setUniform2f(name: String, x: Float, y: Float) {
        GL20.glUniform2f(getUniformLocation(name), x, y)
    }

    fun setUniform3f(name: String, x: Float, y: Float, z: Float) {
        GL20.glUniform3f(getUniformLocation(name), x, y, z)
    }

    fun setUniform4f(name: String, x: Float, y: Float, z: Float, w: Float) {
        GL20.glUniform4f(getUniformLocation(name), x, y, z, w)
    }

    fun delete() {
        GL20.glDeleteProgram(id)
    }

    fun id(): Int = id

    private class ShaderLinkException(msg: String) : RuntimeException(msg)
}