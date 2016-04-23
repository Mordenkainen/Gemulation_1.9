package com.m4thg33k.gemulation.client.gui;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.inventory.ContainerGemBag;
import com.m4thg33k.gemulation.inventory.SlotGem;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gems.item.ModItems;

import java.util.List;

public class GuiGemBag extends GuiContainer{

    private static final ResourceLocation texture = new ResourceLocation(Gemulation.MODID+":textures/gui/"+ Names.GEM_BAG + ".png");

    public GuiGemBag(EntityPlayer player)
    {
        super(new ContainerGemBag(player));

        this.xSize = 183;
        this.ySize = 165;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        mc.getTextureManager().bindTexture(texture);
        int w = (width-xSize)/2;
        int h = (height-ySize)/2;
        drawTexturedModalRect(w,h,0,0,xSize,ySize);

//        List<Slot> slotList = inventorySlots.inventorySlots;
//        for (Slot slot : slotList)
//        {
//            if (slot instanceof SlotGem)
//            {
//                SlotGem slotg = (SlotGem)slot;
//                if (!slotg.getHasStack())
//                {
//                    ItemStack stack = new ItemStack(ModItems.gem,0,slotg.getGemData());
//                    int x = guiLeft + slotg.xDisplayPosition;
//                    int y = guiTop + slotg.yDisplayPosition;
//                    RenderHelper.enableGUIStandardItemLighting();
//                    mc.getRenderItem().renderItemIntoGUI(stack,x,y);
//                    RenderHelper.disableStandardItemLighting();
//                    mc.fontRendererObj.drawStringWithShadow("0",x+11,y+9,0xFF6666);
//                }
//            }
//        }
    }

    @Override
    protected boolean checkHotbarKeys(int keyCode) {
        return false;
    }
}
