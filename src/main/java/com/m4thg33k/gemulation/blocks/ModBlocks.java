package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.blocks.itemBlocks.GemFurnaceItemBlock;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static GemFurnaceBlock gemFurnaceBlock;// = new GemFurnaceBlock(false);
    public static GemFurnaceBlock darkGemFurnaceBlock;// = new GemFurnaceBlock(true);

    public static void createBlocks()
    {
        gemFurnaceBlock = new GemFurnaceBlock(false);
        darkGemFurnaceBlock = new GemFurnaceBlock(true);

        GameRegistry.registerBlock(gemFurnaceBlock, GemFurnaceItemBlock.class, Names.GEM_FURNACE);
        GameRegistry.registerBlock(darkGemFurnaceBlock,GemFurnaceItemBlock.class, Names.DARK_GEM_FURNACE);
    }
}
