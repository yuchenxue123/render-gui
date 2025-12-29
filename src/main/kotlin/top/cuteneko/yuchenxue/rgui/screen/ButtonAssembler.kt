package top.cuteneko.yuchenxue.rgui.screen

import top.cuteneko.yuchenxue.rgui.MinecraftShortcut

class ButtonAssembler(
    // Button Max Width
    private val width: Int,
    // Button Height
    private val height: Int
) : Component, MinecraftShortcut {

    companion object {

        const val VERTICAL_SPACE = 8

        const val HORIZONTAL_SPACE = 16

    }

    private var alignment: Alignment = Alignment.CENTER

    private var row = 1

    private val buttons = mutableMapOf<Int, MutableList<Button>>()

    fun add(button: Button) {
        val buttons = buttons.getOrPut(row) { mutableListOf() }
        buttons.add(button)
    }

    fun next() {
        row += 1
    }

    fun assemble(): Component {

        buttons.forEach { (row, buttons) ->

            val size = buttons.size

            if (size == 0 ) {
                return@forEach
            }

            val buttonWidth = width - (size - 1) * HORIZONTAL_SPACE / size



        }

        return this
    }

}