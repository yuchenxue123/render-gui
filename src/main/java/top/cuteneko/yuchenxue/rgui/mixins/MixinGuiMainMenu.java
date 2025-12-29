package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.cuteneko.yuchenxue.rgui.render.BackgroundShader;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends MixinGuiScreen {

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/GuiMainMenu;renderSkybox(IIF)V", ordinal = 0, shift = At.Shift.AFTER))
    private void over(int mouseX, int mouseY, float deltaTime, CallbackInfo info) {
        BackgroundShader.INSTANCE.drawBackground(this.width, this.height);
    }

}
