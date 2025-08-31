package adventurersbelt;

import adventurersbelt.mixin.MinecraftMixin;
import adventurersbelt.settings.BeltSettings;
import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.Minecraft;

import java.io.IOException;
import java.util.Map;

public class AdventurersBeltAddon extends BTWAddon {

    public static AdventurersBeltAddon instance = new AdventurersBeltAddon();

    public AdventurersBeltAddon() {
        super();
    }

    public static Boolean shouldShowDateTimer;

    public void preInitialize() {
        this.registerProperty("EnableMinecraftDateTimer", "True", "Set if the minecraft date should show up or not");
        registerProperties();
    }

    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        shouldShowDateTimer = Boolean.parseBoolean(propertyValues.get("EnableMinecraftDateTimer"));
        BeltSettings.beltSettings.loadOptions();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    public void registerProperties(){
        this.registerProperty("clockX", "0.5");
    }
}