package com.m4thg33k.gemulation.client.gui;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.inventory.ContainerGemChanger;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.gemulation.tiles.TileGemChanger;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gems.init.ModItems;

import java.util.ArrayList;
import java.util.List;

public class GuiGemChanger extends GuiContainer {

    private TileGemChanger tileGemChanger;
    private InventoryPlayer playerInventory;

    public GuiGemChanger(InventoryPlayer playerInventory, TileGemChanger te)
    {
        super(new ContainerGemChanger(playerInventory,te));

        this.tileGemChanger = te;
        this.playerInventory = playerInventory;

        this.xSize = 328;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int w = (width-xSize)/2;
        int h = (height-ySize)/2;

        int mX = mouseX - w;
        int mY = mouseY - h;

        String name = tileGemChanger.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(name,xSize/2-this.fontRenderer.getStringWidth(name)/2,6,0x404040);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(),83,ySize-96+2,0x404040);

//        String energy = ((ContainerGemChanger)inventorySlots).storedEnergy + " / " + tileGemChanger.getMaxEnergyStored();
//        this.fontRenderer.drawString(energy,xSize/2-this.fontRenderer.getStringWidth(energy)/2,65,0x404040);

        if (mX<176 || mX>=179 || mY<26 || mY >=60)
        {
            return;
        }

        int stored = ((ContainerGemChanger)inventorySlots).storedEnergy;
        double perc = ((int)(10000*((((double)stored)/TileGemChanger.MAX_ENERGY))))/100.0;
//        double perc = (((int)(((double)stored/(double)tileGemChanger.getMaxEnergyStored())*10000))%100)/100.0;
        int num = stored/TileGemChanger.ENERGY_NEEDED_TO_CHANGE;
        //String text = "Can convert";
//        String text2 = num+" gems.";

        List<String> text = new ArrayList<String>();
        text.add(perc+"%");
        text.add("Can convert");
        text.add(num+" gems.");

        this.drawHoveringText(text,mX,mY);

//        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
//        mc.getTextureManager().bindTexture(new ResourceLocation(Gemulation.MODID+":textures/gui/"+ Names.GEM_CHANGER + ".png"));
////        this.drawTexturedModalRect(mouseX-w-2,mouseY-h-16,0,202,this.fontRenderer.getStringWidth(text)+4,20);
//
//        this.fontRenderer.drawString(perc+"%",mX,mY-14,0x404040,false);
//        this.fontRenderer.drawString(text,mX,mY-6,0x404040,false);
//        this.fontRenderer.drawString(text2,mX+this.fontRenderer.getStringWidth("  "),mY+2,0x404040,false);

//        mc.getTextureManager().bindTexture(new ResourceLocation(Gemulation.MODID+":textures/gui/"+ Names.GEM_CHANGER + "Foreground.png"));
//        this.drawTexturedModalRect(0,0,0,0,xSize,ySize);
    }



    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        int k = (width-xSize)/2;
        int l = (height-ySize)/2;



        mc.getTextureManager().bindTexture(new ResourceLocation(Gemulation.MODID+":textures/gui/"+ Names.GEM_CHANGER + ".png"));
        this.drawModalRectWithCustomSizedTexture(k,l,0,0,xSize,ySize,512,512);
//        this.drawTexturedModalRect(k,l,0,0,xSize,ySize);

        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        int L = getWorkTimeScaled(34);
        this.drawModalRectWithCustomSizedTexture(k+149,l+26+L,0,201-34+L,3,34-L,512,512);
//        this.drawTexturedModalRect(k+113,l+26+L,0,201-34+L,3,34-L);
        L = getEnergyStoredScaled(34);
//        this.drawTexturedModalRect(k+140,l+60-L,4,200-L,3,L);
        this.drawModalRectWithCustomSizedTexture(k+176,l+60-L,4,200-L,3,L,512,512);

//        drawGemBackgrounds(k,l);
//        zLevel = 2;
//        mc.getTextureManager().bindTexture(new ResourceLocation(Gemulation.MODID+":textures/gui/"+ Names.GEM_CHANGER + "Foreground.png"));
//        this.drawTexturedModalRect(k,l,0,0,xSize,ySize);
    }

    private int getWorkTimeScaled(int pixels)
    {
        int i = ((ContainerGemChanger)this.inventorySlots).workValue;
        return i==0? pixels : i*pixels/TileGemChanger.WORK_TIME;
    }

    private int getEnergyStoredScaled(int pixels)
    {
        int i = ((ContainerGemChanger)this.inventorySlots).storedEnergy;
        return i==0? 0 : i*pixels/TileGemChanger.MAX_ENERGY;
    }

    private void drawGemBackgrounds(int k, int l)
    {
//        for (int x=0;x<2;x++)
//        {
//            for (int y=0;y<6;y++)
//            {
//                StackRenderingHelper.renderItemStack(itemRender.getItemModelMesher(),mc.renderEngine,k+8+18*x,l+8+18*y,new ItemStack(ModItems.gem,1,x+2*y),0xFFFFFF,true);
//            }
//        }

        for (int x=0;x<2;x++)
        {
            for (int y=0;y<6;y++)
            {
                itemRender.renderItemIntoGUI(new ItemStack(ModItems.gem,1,x+2*y),k+8+18*x,l+8+18*y);
//                itemRender.renderItemIntoGUI(new ItemStack(ModItems.gem,1,x+2*y),k+214+18*x,l+8+18*y);
            }
        }
//        GlStateManager.disableBlend();
//        GlStateManager.disableAlpha();
    }
}