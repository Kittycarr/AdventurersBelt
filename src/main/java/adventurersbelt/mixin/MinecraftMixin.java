package adventurersbelt.mixin;

import adventurersbelt.settings.BeltSettings;
import net.minecraft.src.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow private static Minecraft theMinecraft;

    public BeltSettings beltSettings;
    public File mcBeltData;

    @Inject(method = "startGame", at = @At("HEAD"))
    public void idfk(CallbackInfo ci) {
    }

}
