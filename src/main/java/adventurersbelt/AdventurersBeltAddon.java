package adventurersbelt;

import adventurersbelt.mixin.MinecraftMixin;
import adventurersbelt.settings.BeltSettings;
import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.Minecraft;

import java.io.IOException;
import java.util.Map;

public class AdventurersBeltAddon extends BTWAddon {
    public static AdventurersBeltAddon instance;
    public static BeltSettings settings = new BeltSettings();

    public AdventurersBeltAddon() {
        super();
        instance = this;
    }

    public static Boolean shouldShowDateTimer;

    public void preInitialize() {
        this.registerProperty("EnableMinecraftDateTimer", "True", "Set if the minecraft date should show up or not");
        registerPropertiesMap(BeltSettings.getProperties());
    }

    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        shouldShowDateTimer = Boolean.parseBoolean(propertyValues.get("EnableMinecraftDateTimer"));
        settings.loadOptions();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    /**
     * Registers properties in provided k/v set
     */
    public void registerPropertiesMap(Map<String, String> properties) {
        for (String key : properties.keySet()) {
            this.registerProperty(key, properties.get(key));
        }
    }
}