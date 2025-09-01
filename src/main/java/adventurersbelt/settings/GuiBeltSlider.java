package adventurersbelt.settings;

import adventurersbelt.AdventurersBeltAddon;
import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiBeltSlider extends GuiButton {

    public float sliderValue = 1.0F;
    public boolean dragging;

    public GuiBeltSlider(int id, int j, int k, String string, float f) {
        super(id, j, k, 150, 20, string);
        this.sliderValue = f;

    }

    protected int getHoverState(boolean bl) {
        return 0;
    }

    protected void mouseDragged(Minecraft minecraft, int i, int j) {
        if (this.drawButton) {
            if (this.dragging) {
                this.sliderValue = (float)(i - (this.xPosition + 4)) / (float)(this.width - 8);
                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }
                AdventurersBeltAddon.settings.setOptionFloatValue(this, this.sliderValue);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        if (super.mousePressed(minecraft, i, j)) {
            this.sliderValue = (float)(i - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            AdventurersBeltAddon.settings.setOptionFloatValue(this, this.sliderValue);
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    public void mouseReleased(int i, int j) {
        this.dragging = false;
    }
}
