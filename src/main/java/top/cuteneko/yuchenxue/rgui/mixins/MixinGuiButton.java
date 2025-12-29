package top.cuteneko.yuchenxue.rgui.mixins;

import net.minecraft.FontRenderer;
import net.minecraft.GuiButton;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.cuteneko.yuchenxue.rgui.render.RoundRectangleShader;

import java.awt.*;

@Mixin(GuiButton.class)
public abstract class MixinGuiButton extends MixinGui {

    @Shadow
    public int xPosition;

    @Shadow
    public int yPosition;

    @Shadow
    protected int width;

    @Shadow
    protected int height;

    @Shadow
    public String displayString;

    @Shadow
    public boolean enabled;

    @Shadow
    protected boolean field_82253_i;

    /**
     * @author 1
     * @reason 1
     */
    @Overwrite
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {

        FontRenderer font = mc.fontRenderer;

        RoundRectangleShader.INSTANCE.drawRoundRectangle(
                this.xPosition,
                this.yPosition,
                this.width,
                this.height,
                5f,
                new Color(40, 40, 40, 120)
        );

        int color = 14737632;
        if (!this.enabled) {
            color = -6250336;
        } else if (this.field_82253_i) {
            color = 16777120;
        }

        this.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, color);

    }


}
