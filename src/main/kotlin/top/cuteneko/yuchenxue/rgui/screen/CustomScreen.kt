package top.cuteneko.yuchenxue.rgui.screen

import net.minecraft.GuiScreen

class CustomScreen(
    val screen: Screen
) : GuiScreen() {

    override fun drawScreen(mouseX: Int, mouseY: Int, deltaTime: Float) {
        screen.render(mouseX, mouseY, deltaTime)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        screen.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseMovedOrUp(mouseX: Int, mouseY: Int, button: Int) {
        screen.mouseReleased(mouseX, mouseY, button)
    }

    override fun keyTyped(char: Char, key: Int) {
        if (screen.shouldClose()) {
            super.keyTyped(char, key)
        }

        screen.keyPressed(char, key)
    }

    override fun doesGuiPauseGame(): Boolean = screen.shouldPause()

}