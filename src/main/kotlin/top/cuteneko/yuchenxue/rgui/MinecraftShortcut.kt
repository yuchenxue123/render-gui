package top.cuteneko.yuchenxue.rgui

import net.minecraft.EntityClientPlayerMP
import net.minecraft.Minecraft
import net.minecraft.WorldClient

val mc: Minecraft
    inline get() = Minecraft.getMinecraft()

val player: EntityClientPlayerMP
    inline get() = mc.thePlayer

val world: WorldClient
    inline get() = mc.theWorld

interface MinecraftShortcut {

    val mc: Minecraft
        get() = Minecraft.getMinecraft()

    val player: EntityClientPlayerMP
        get() = mc.thePlayer

    val world: WorldClient
        get() = mc.theWorld

}