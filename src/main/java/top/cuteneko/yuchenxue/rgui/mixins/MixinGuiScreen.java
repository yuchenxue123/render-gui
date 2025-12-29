package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {
    @Shadow
    public int height;
    @Shadow
    public int width;
}
