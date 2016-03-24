package com.m4thg33k.gemulation.core.proxies;

import com.m4thg33k.gemulation.blocks.ModBlocks;
import com.m4thg33k.gemulation.core.init.MiscInits;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e)
    {
        MiscInits.addFurnaceTypes();
//        GemulationPackets.init();
//        ModItems.createItems();
        ModBlocks.createBlocks();
    }

    public void init(FMLInitializationEvent e)
    {
//        NetworkRegistry.INSTANCE.registerGuiHandler(Gemulation.instance,new GemulationGuiHandler());
////        this.registerRenderInformation();
//        ModTiles.init();
//        ModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e)
    {

    }

//    public void handleNBTPacket(PacketNBT message)
//    {
//
//    }
//
//    public void registerRenderInformation()
//    {
//
//    }
//
//    public <T extends TileGemChest> void registerTileEntitySpecialRenderer(Class<T> type)
//    {
//
//    }
}
