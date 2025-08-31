package adventurersbelt.mixin;

import adventurersbelt.settings.BeltSettings;
import adventurersbelt.settings.GuiBeltButton;
import adventurersbelt.settings.GuiBeltOptions;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameMenu.class)
public class GuiInGameMenuMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("TAIL"))
    public void InitBeltButton(CallbackInfo cbi){
        byte var1 = -16;
        this.buttonList.add(new GuiBeltButton(6969,(this.width / 2 - 100)-24 , this.height / 4 + 96 + var1, 20, 20, "Belt"));
    }
    @Inject(method = "actionPerformed", at = @At("HEAD"))
    public void MakeButtonDoShit(GuiButton guiButton, CallbackInfo ci){
        if (guiButton.id == 6969) {
            this.mc.displayGuiScreen(new GuiBeltOptions(this, BeltSettings.beltSettings));
        }
    }
}
