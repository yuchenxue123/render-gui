package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.FontRenderer;
import net.minecraft.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public abstract class MixinGui {
    @Shadow
    public abstract void drawCenteredString(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5);
}
