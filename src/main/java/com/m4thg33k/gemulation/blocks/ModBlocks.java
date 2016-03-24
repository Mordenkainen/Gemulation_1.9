package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.lib.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static GemFurnaceBlock gemFurnaceBlock = new GemFurnaceBlock(false);
    public static GemFurnaceBlock darkGemFurnaceBlock = new GemFurnaceBlock(true);

    public static void createBlocks()
    {
        GameRegistry.registerBlock(gemFurnaceBlock, Names.GEM_FURNACE);
        GameRegistry.registerBlock(darkGemFurnaceBlock, Names.GEM_FURNACE);
    }
}
