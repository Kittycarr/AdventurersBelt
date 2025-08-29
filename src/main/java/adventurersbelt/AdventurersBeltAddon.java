package adventurersbelt;

import adventurersbelt.mixin.MinecraftMixin;
import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.Minecraft;

import java.io.IOException;
import java.util.Map;

public class AdventurersBeltAddon extends BTWAddon {
    public static AdventurersBeltAddon instance;





    public AdventurersBeltAddon() {
        super();
    }

    public static Boolean shouldShowDateTimer;

    public void preInitialize() {
        this.registerProperty("EnableMinecraftDateTimer", "True", "Set if the minecraft date should show up or not");

    }

    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        shouldShowDateTimer = Boolean.parseBoolean(propertyValues.get("EnableMinecraftDateTimer"));

    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}