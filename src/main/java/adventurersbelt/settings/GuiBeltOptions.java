package adventurersbelt.settings;

import adventurersbelt.AdventurersBeltAddon;
import net.minecraft.src.*;

public class GuiBeltOptions extends GuiScreen {


    private final GuiScreen parentScreen;
    private final BeltSettings options;
    protected String screenTitle = "Belt Options";

    public GuiBeltOptions(GuiScreen par1GuiScreen, BeltSettings par2GameSettings){
        this.parentScreen = par1GuiScreen;
        this.options = par2GameSettings;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(6970, this.width / 2 - 75, this.height / 6 + 174 - 6, 150, 20, "Save"));

        this.buttonList.add(new GuiBeltSlider(6971, this.width / 2 - 152, this.height / 6 + 96 - 6, "clock X", AdventurersBeltAddon.settings.clockPlacementX));
        this.buttonList.add(new GuiBeltSlider(6972, this.width / 2 + 2, this.height / 6 + 96 - 6, "clock Y", AdventurersBeltAddon.settings.clockPlacementY));
        this.buttonList.add(new GuiBeltSlider(6973, this.width / 2 - 152, this.height / 6 + 120 - 6, "compass X", AdventurersBeltAddon.settings.compassPlacementX));
        this.buttonList.add(new GuiBeltSlider(6974, this.width / 2 + 2, this.height / 6 + 120 - 6, "compass Y", AdventurersBeltAddon.settings.compassPlacementY));
        this.buttonList.add(new GuiBeltSlider(6975, this.width / 2 - 152, this.height / 6 + 144 - 6, "calender X", AdventurersBeltAddon.settings.calenderPlacementX));
        this.buttonList.add(new GuiBeltSlider(6976, this.width / 2 + 2, this.height / 6 + 144 - 6, "calender Y", AdventurersBeltAddon.settings.calenderPlacementY));

//        this.buttonList.add(new GuiSlider(6971, this.width / 2 - 152, this.height / 6 + 96 - 6, var2, "", 0));
//        this.buttonList.add(new GuiSlider(6972, this.width / 2 + 2, this.height / 6 + 96 - 6, var2, "", 0));
//        this.buttonList.add(new GuiSlider(6973, this.width / 2 - 152, this.height / 6 + 120 - 6, var2, "", 0));
//        this.buttonList.add(new GuiSlider(6974, this.width / 2 + 2, this.height / 6 + 120 - 6, var2, "", 0));
//        this.buttonList.add(new GuiSlider(6975, this.width / 2 - 152, this.height / 6 + 144 - 6, var2, "", 0));
//        this.buttonList.add(new GuiSlider(6976, this.width / 2 + 2, this.height / 6 + 144 - 6, var2, "", 0));

    }

    public void actionPerformed(GuiButton guiButton){
        switch(guiButton.id) {
            case 6970:
                mc.displayGuiScreen((this.parentScreen));
                AdventurersBeltAddon.settings.saveOptions();
                break;
            case 6971:

        }
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 15, 16777215);
        super.drawScreen(par1, par2, par3);
    }
}

