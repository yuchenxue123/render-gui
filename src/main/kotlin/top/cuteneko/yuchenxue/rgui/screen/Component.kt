package top.cuteneko.yuchenxue.rgui.screen

interface Component {

    fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {}

    fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {}

    fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {}

    fun keyPressed(char: Char, key: Int) {}


    companion object {

        fun empty(): Component {
            return object : Component {}
        }

    }
}