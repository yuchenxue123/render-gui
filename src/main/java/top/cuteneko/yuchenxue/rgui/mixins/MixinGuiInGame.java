package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.GuiIngame;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.cuteneko.yuchenxue.rgui.render.SimpleFrameBuffer;

@Mixin(GuiIngame.class)
public class MixinGuiInGame {

    @Shadow
    @Final
    private Minecraft mc;

    // 怎么这一坨渲染都在一个方法里
    // 这里应该是渲染完 tooltip
    @Inject(
            method = "renderGameOverlay",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/GuiIngame;drawTexturedModalRect(IIIIII)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void hookRender(float deltaTime, boolean par2, int par3, int par4, CallbackInfo ci) {
        SimpleFrameBuffer.INSTANCE.render(mc.displayWidth, mc.displayHeight);
    }
}
