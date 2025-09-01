package adventurersbelt.settings;

import adventurersbelt.AdventurersBeltAddon;
import net.minecraft.src.I18n;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.*;

public class BeltSettings {
    /**
     * Properties of the mod.
     */
    protected static final HashMap<String, String> PROPERTIES = new HashMap<>();
    public Minecraft mc;

    public float clockPlacementX;
    public float clockPlacementY;
    public float compassPlacementX;
    public float compassPlacementY;
    public float calenderPlacementX;
    public float calenderPlacementY;
    public KeyBinding accessPageKey;

    public BeltSettings() {
        // Set objects
        accessPageKey = new KeyBinding("key.accesspage", 10);
        mc = Minecraft.getMinecraft();
    }

    public static HashMap<String, String> getProperties() {
        return PROPERTIES;
    }

    public String getKeyBindingDescription() {
        return I18n.getString(this.accessPageKey.keyDescription);
    }

    public String getOptionDisplayString() {
        int var2 = this.accessPageKey.keyCode;
        return getKeyDisplayString(var2);
    }

    public static String getKeyDisplayString(int par0) {
        return par0 < 0 ? I18n.getStringParams("key.mouseButton", new Object[]{par0 + 101}) : Keyboard.getKeyName(par0);
    }

    public static boolean isKeyDown(KeyBinding par0KeyBinding) {
        return par0KeyBinding.keyCode < 0 ? Mouse.isButtonDown(par0KeyBinding.keyCode + 100) : Keyboard.isKeyDown(par0KeyBinding.keyCode);
    }

    public void setKeyBinding(int par2) {
        this.accessPageKey.keyCode = par2;
    }

    public void setOptionFloatValue(GuiBeltSlider guiBeltSlider, float sliderValue) {
        switch (guiBeltSlider.id) {
            case 6971:
                clockPlacementX = sliderValue;
                break;
            case 6972:
                clockPlacementY = sliderValue;
                break;
            case 6973:
                compassPlacementX = sliderValue;
            case 6974:
                compassPlacementY = sliderValue;
            case 6975:
                calenderPlacementX = sliderValue;
            case 6976:
                calenderPlacementY = sliderValue;
        }
    }

    /**
     * Maps local properties to the {@code PROPERTIES} map
     */
    private void localToMappedProperties() {
        PROPERTIES.put("clockX", String.valueOf(clockPlacementX));
        PROPERTIES.put("clockY", String.valueOf(clockPlacementY));
        PROPERTIES.put("compassX", String.valueOf(compassPlacementX));
        PROPERTIES.put("compassY", String.valueOf(compassPlacementY));
        PROPERTIES.put("calendarX", String.valueOf(calenderPlacementX));
        PROPERTIES.put("calendarY", String.valueOf(calenderPlacementY));
    }

    /**
     * Sets local properties based on the provided hash map
     */
    private void mappedToLocalProperties(@NotNull HashMap<String, String> map) {
        clockPlacementX = Float.parseFloat(
                map.getOrDefault("clockX", PROPERTIES.get("clockX")));
        clockPlacementY = Float.parseFloat(
                map.getOrDefault("clockY", PROPERTIES.get("clockY")));
        compassPlacementX = Float.parseFloat(
                map.getOrDefault("compassX", PROPERTIES.get("compassX")));
        compassPlacementY = Float.parseFloat(
                map.getOrDefault("compassY", PROPERTIES.get("compassY")));
        calenderPlacementX = Float.parseFloat(
                map.getOrDefault("calendarX", PROPERTIES.get("calendarX")));
        calenderPlacementY = Float.parseFloat(
                map.getOrDefault("calendarY", PROPERTIES.get("calendarY")));
    }

    /**
     * Saves local values to config
     */
    public void saveOptions() {
        // First, map our current values to the PROPERTIES map
        localToMappedProperties();

        Map <String, String> OLD = AdventurersBeltAddon.instance.loadConfigProperties();
        Map <String, String> NEW = new HashMap<>();
        if (OLD != null) {
            // Previous properties exist, so begin updating
            for (String propertyName : PROPERTIES.keySet()) {
                // If the two values don't match...
                if (!PROPERTIES.get(propertyName).equals(OLD.get(propertyName))) {
                    // Put the new value in
                    NEW.put(propertyName, PROPERTIES.get(propertyName));
                } else {
                    // Otherwise, copy over the old property
                    NEW.put(propertyName, OLD.get(propertyName));
                }
            }
        }
        else {
            // No old config, so just copy on over
            saveDefaultConfig();
            return;
        }

        // Rebuild the config
        AdventurersBeltAddon.instance.repopulateConfigFile(NEW);
    }

    /**
     * Saves the default config, based on values in {@code PROPERTIES}
     */
    private HashMap<String, String> saveDefaultConfig() {
        AdventurersBeltAddon.instance.repopulateConfigFile(PROPERTIES);
        return PROPERTIES;
    }

    /**
     * Loads and validates the config file
     */
    public void loadOptions() {
        Map<String, String> LOAD = AdventurersBeltAddon.instance.loadConfigProperties();
        HashMap<String, String> NEW = new HashMap<>();

        // Check if properties have been saved before
        if (LOAD != null) {
            // Properties exist, so lets start setting values
            for (String property : PROPERTIES.keySet()) {
                // Get property from loaded
                String value = LOAD.getOrDefault(property, "");
                if (!value.isEmpty()) {
                    // Not empty, put value in
                    NEW.put(property, value);
                } else {
                    // Is empty, meaning the config does not contain
                    // a value we need. So just put the default
                    // value in
                    NEW.put(property, PROPERTIES.get(property));
                }
            }
        } else {
            // No config exists, so make one
            NEW = saveDefaultConfig();
        }

        // Set values
        mappedToLocalProperties(NEW);
    }

    static {
        PROPERTIES.put("clockX", "0.5F");
        PROPERTIES.put("clockY", "0.5F");
        PROPERTIES.put("compassX", "0.5F");
        PROPERTIES.put("compassY", "0.5F");
        PROPERTIES.put("calendarX", "0.5F");
        PROPERTIES.put("calendarY", "0.5F");
    }

}
