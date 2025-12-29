package top.cuteneko.yuchenxue.rgui

import net.minecraft.Minecraft
import top.cuteneko.yuchenxue.rgui.screen.CustomScreen
import top.cuteneko.yuchenxue.rgui.screen.Screen

fun Minecraft.setScreen(screen: Screen) {
    displayGuiScreen(CustomScreen(screen))
}