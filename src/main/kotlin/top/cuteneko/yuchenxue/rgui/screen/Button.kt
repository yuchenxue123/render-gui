package top.cuteneko.yuchenxue.rgui.screen

import top.cuteneko.yuchenxue.rgui.mc
import top.cuteneko.yuchenxue.rgui.render.RoundRectangleShader
import java.awt.Color

class Button(
    val id: Int,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    private val onClick: () -> Unit = {}
) : Component {

    private val font = mc.fontRenderer

    override fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {

//        RoundRectangleShader.drawRoundRectangle(
//            x,
//            y,
//            width,
//            height,
//            1f,
//            Color(0, 0, 0, 180)
//        )
//


    }

}