package com.m4thg33k.gemulation.blocks.itemBlocks;

import com.m4thg33k.gemulation.blocks.ModBlocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItemBlocks {

    public static final GemFurnaceItemBlock gemFurnaceItemBlock = new GemFurnaceItemBlock(ModBlocks.gemFurnaceBlock);
    public static final DarkGemFurnaceItemBlock darkGemFurnaceItemBlock = new DarkGemFurnaceItemBlock(ModBlocks.darkGemFurnaceBlock);

    public static final GemChestItemBlock gemChestItemBlock = new GemChestItemBlock(ModBlocks.gemChestBlock);
    public static final DarkGemChestItemBlock darkGemChestItemBlock = new DarkGemChestItemBlock(ModBlocks.darkGemChestBlock);
    public static final SuperGemChestItemBlock superGemChestItemBlock = new SuperGemChestItemBlock(ModBlocks.superGemChestBlock);
    public static final DarkSuperGemChestItemBlock darkSuperGemChestItemBlock = new DarkSuperGemChestItemBlock(ModBlocks.darkSuperGemChestBlock);


    public static void create()
    {
        GameRegistry.register(gemFurnaceItemBlock);
        GameRegistry.register(darkGemFurnaceItemBlock);

        GameRegistry.register(gemChestItemBlock);
        GameRegistry.register(darkGemChestItemBlock);
        GameRegistry.register(superGemChestItemBlock);
        GameRegistry.register(darkSuperGemChestItemBlock);
    }
}
