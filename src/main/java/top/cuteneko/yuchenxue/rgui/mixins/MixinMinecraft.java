package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.GuiMainMenu;
import net.minecraft.GuiMultiplayer;
import net.minecraft.GuiScreen;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.cuteneko.yuchenxue.rgui.MinecraftExtensionKt;
import top.cuteneko.yuchenxue.rgui.screen.CustomScreen;
import top.cuteneko.yuchenxue.rgui.screen.MainScreen;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    public abstract void displayGuiScreen(GuiScreen par1GuiScreen);

    @Shadow
    public static Minecraft theMinecraft;

    @Inject(
            method = "displayGuiScreen",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void hookScreen(GuiScreen screen, CallbackInfo ci) {

//        if (screen instanceof GuiMainMenu) {
//
//            screen.setWorldAndResolution(theMinecraft, theMinecraft.displayWidth, theMinecraft.displayHeight);
//
//            MainScreen instance = new MainScreen((GuiMainMenu) screen);
//
//            this.displayGuiScreen(new CustomScreen(instance));
//            ci.cancel();
//        }

    }

}
