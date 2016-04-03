package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.blocks.itemBlocks.GemChestItemBlock;
import com.m4thg33k.gemulation.blocks.itemBlocks.GemFurnaceItemBlock;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static GemFurnaceBlock gemFurnaceBlock = new GemFurnaceBlock();
    public static DarkGemFurnaceBlock darkGemFurnaceBlock = new DarkGemFurnaceBlock();
    public static GemChestBlock gemChestBlock = new GemChestBlock();
    public static DarkGemChestBlock darkGemChestBlock = new DarkGemChestBlock();
    public static SuperGemChestBlock superGemChestBlock = new SuperGemChestBlock();
    public static DarkSuperGemChestBlock darkSuperGemChestBlock = new DarkSuperGemChestBlock();

    public static GemChangerBlock gemChangerBlock = new GemChangerBlock();

    public static void createBlocks()
    {

        GameRegistry.registerBlock(gemFurnaceBlock, GemFurnaceItemBlock.class, Names.GEM_FURNACE);
        GameRegistry.registerBlock(darkGemFurnaceBlock,GemFurnaceItemBlock.class, Names.DARK_GEM_FURNACE);
        GameRegistry.registerBlock(gemChestBlock, GemChestItemBlock.class,Names.GEM_CHEST);
        GameRegistry.registerBlock(darkGemChestBlock, GemChestItemBlock.class,Names.DARK_GEM_CHEST);
        GameRegistry.registerBlock(superGemChestBlock,GemChestItemBlock.class,Names.SUPER_GEM_CHEST);
        GameRegistry.registerBlock(darkSuperGemChestBlock,GemChestItemBlock.class,Names.DARK_SUPER_GEM_CHEST);

        GameRegistry.registerBlock(gemChangerBlock,Names.GEM_CHANGER);

    }
}
