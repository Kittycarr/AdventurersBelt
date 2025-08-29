package adventurersbelt.mixin;

import adventurersbelt.AdventurersBeltAddon;
import btw.util.status.StatusEffect;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Material;
import net.minecraft.src.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(GuiIngame.class)
public class GuiInGameMixin {

    @Shadow
    private Minecraft mc;

    private int amountRendered = 0;

    @Inject(method = "Lnet/minecraft/src/GuiIngame;drawPenaltyText(II)V", at = @At("TAIL"))
    private void drawTimer(int iScreenX, int iScreenY, CallbackInfo cbi) {
        if (!mc.thePlayer.isDead) {
            amountRendered = 0;
            if(this.mc.thePlayer.isInsideOfMaterial(Material.water) || mc.thePlayer.getAir() < 300 ){
                amountRendered++;
            }
            FontRenderer fontRenderer = this.mc.fontRenderer;
            String textToShow = "" + (((int) Math.ceil(Minecraft.getMinecraft().theWorld.getWorldTime() / 24000)) + 1);
            int stringWidth = fontRenderer.getStringWidth(textToShow);
            ArrayList<StatusEffect> activeStatuses = mc.thePlayer.getAllActiveStatusEffects();
            if (AdventurersBeltAddon.shouldShowDateTimer) {
                renderText(textToShow, stringWidth, iScreenX, iScreenY, fontRenderer, activeStatuses);
            }
        }
    }

    private void renderText(String text, int stringWidth, int iScreenX, int iScreenY, FontRenderer fontRenderer, ArrayList<StatusEffect> activeStatuses){
        fontRenderer.drawString(text, iScreenX - stringWidth + 100, iScreenY, 0);
        amountRendered++;
    }
}
