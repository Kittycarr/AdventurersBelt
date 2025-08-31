package adventurersbelt.onscreen;

import btw.client.texture.CustomUpdatingTexture;
import net.minecraft.src.*;

public class GuiOnScreen extends GuiIngame {

    public static final GuiOnScreen guiOnScreen = new GuiOnScreen(Minecraft.getMinecraft());
    public Minecraft mc = Minecraft.getMinecraft();
    RenderItem itemRenderer = new RenderItem();

    public GuiOnScreen(Minecraft par1Minecraft) {
        super(par1Minecraft);
    }

    public void renderItem(){
        int itemID = Item.pocketSundial.itemID;
        if(itemID == Item.pocketSundial.itemID){
            ItemStack clock = new ItemStack(Item.pocketSundial);
            Icon clockIcon = clock.getItem().getIconFromDamage(0);
            if (clockIcon instanceof CustomUpdatingTexture) {
                CustomUpdatingTexture customUpdateTexture = (CustomUpdatingTexture)clockIcon;
                customUpdateTexture.updateActive(CustomUpdatingTexture.DRAW_SLOT);
            }

            itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), clock, 10, 10);

            //itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), clock, 1, 1);
        }
    }
}
