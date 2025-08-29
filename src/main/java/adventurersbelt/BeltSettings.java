package adventurersbelt;

import com.prupe.mcpatcher.Config;
import net.minecraft.src.I18n;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import javax.swing.text.JTextComponent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class BeltSettings {

    public static final BeltSettings beltSettings = new BeltSettings();

    public static final Properties properties = new Properties();
    public static final File beltSettingsFile = new File("config/beltsettings.properties");
    public Minecraft mc;

    public float clockPlacementX;
    public float clockPlacementY;
    public float compassPlacementX;
    public float compassPlacementY;
    public float calenderPlacementX;
    public float calenderPlacementY;
    public KeyBinding accessPageKey;


    public BeltSettings() {
        clockPlacementX = 0.5F;
        clockPlacementY = 0.5F;
        compassPlacementX = 0.5F;
        compassPlacementY = 0.5F;
        calenderPlacementX = 0.5F;
        calenderPlacementY = 0.5F;
        accessPageKey = new KeyBinding("key.accesspage", 10);
        mc = Minecraft.getMinecraft();
        this.loadOptions();
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

    public void saveOptions() {

//        properties.setProperty("clockX", String.valueOf(this.clockPlacementX));

//        try (FileOutputStream fileOutputStream = new FileOutputStream(beltSettingsFile)){
//            properties.store(fileOutputStream, "Adventurers Belt Config");
        if (!beltSettingsFile.exists()) {
            try {
                Files.createFile(beltSettingsFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try{
            BufferedWriter bufferedWriter = Files.newBufferedWriter(beltSettingsFile.toPath(), StandardOpenOption.TRUNCATE_EXISTING);
            bufferedWriter.write("clockX" + this.clockPlacementX);
            bufferedWriter.newLine( );

            PrintWriter var1 = new PrintWriter(new FileWriter(beltSettingsFile));
            var1.println("clockX" + this.clockPlacementX);
        } catch (IOException exception) {
            this.mc.getLogAgent().logWarning("Failed to save belt options");
            exception.printStackTrace();
        }

        System.out.println("Worked!");
    }

    public void loadOptions() {

//        try (FileInputStream fileInputStream = new FileInputStream(beltSettingsFile)) {
//            properties.load(fileInputStream);
//        } catch (IOException e) {
//            saveOptions();
//            return;
//        }


        try {
            if (!beltSettingsFile.exists()) {
                return;
            }
            BufferedReader var1 = new BufferedReader(new FileReader(beltSettingsFile));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                try {
                    String[] var3 = var2.split(":");
                    if (var3[0].equals("clockX")){
                        this.clockPlacementX = Float.parseFloat(var3[1]);
                    }
                } catch (Exception var51) {
                    this.mc.getLogAgent().logWarning("Skipping bad option: " + var2);
                }
            }
        }
        catch (Exception var6){
            this.mc.getLogAgent().logWarning("Failed to load options");
            var6.printStackTrace();
        }
    }

}
