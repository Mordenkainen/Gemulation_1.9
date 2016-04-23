package com.m4thg33k.gemulation.gui;

import com.m4thg33k.gemulation.client.gui.GuiGemBag;
import com.m4thg33k.gemulation.client.gui.GuiGemChanger;
import com.m4thg33k.gemulation.inventory.ContainerGemBag;
import com.m4thg33k.gemulation.inventory.ContainerGemChanger;
import com.m4thg33k.gemulation.tiles.TileGemChanger;
import com.m4thg33k.lit.client.gui.GuiImprovedChest;
import com.m4thg33k.lit.inventory.ContainerImprovedChest;
import com.m4thg33k.lit.tiles.TileImprovedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GemulationGuiHandler implements IGuiHandler{
    public static final int GEM_CHEST_GUI = 0;
    public static final int GEM_CHANGER_GUI = 1;
    public static final int GEM_BAG_GUI = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case 0:
                TileImprovedChest tile = (TileImprovedChest)world.getTileEntity(new BlockPos(x,y,z));
                return new ContainerImprovedChest(player.inventory,tile,tile.getType());
            case 1:
                return new ContainerGemChanger(player.inventory,(TileGemChanger)world.getTileEntity(new BlockPos(x,y,z)));
            case 2:
                return new ContainerGemBag(player);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case 0:
                TileImprovedChest tile = (TileImprovedChest)world.getTileEntity(new BlockPos(x,y,z));
                return new GuiImprovedChest(tile.getType(),player.inventory,tile);
            case 1:
                return new GuiGemChanger(player.inventory,(TileGemChanger)world.getTileEntity(new BlockPos(x,y,z)));
            case 2:
                return new GuiGemBag(player);
            default:
                return null;
        }
    }
}
