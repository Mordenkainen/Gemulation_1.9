package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.blocks.itemBlocks.GemChestItemBlock;
import com.m4thg33k.gemulation.blocks.itemBlocks.GemFurnaceItemBlock;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static GemFurnaceBlock gemFurnaceBlock = new GemFurnaceBlock();
    public static DarkGemFurnaceBlock darkGemFurnaceBlock = new DarkGemFurnaceBlock();
    public static GemChestBlock gemChestBlock = new GemChestBlock();
    public static DarkGemChestBlock darkGemChestBlock = new DarkGemChestBlock();
    public static SuperGemChestBlock superGemChestBlock = new SuperGemChestBlock();
    public static DarkSuperGemChestBlock darkSuperGemChestBlock = new DarkSuperGemChestBlock();

    public static GemChangerBlock gemChangerBlock = new GemChangerBlock();

    public static GemCraftingTableBlock gemCraftingTableBlock = new GemCraftingTableBlock();
    public static DarkGemCraftingTableBlock darkGemCraftingTableBlock = new DarkGemCraftingTableBlock();


    public static void createBlocks()
    {
        GameRegistry.register(gemFurnaceBlock);
        GameRegistry.register(darkGemFurnaceBlock);
        GameRegistry.register(gemChestBlock);
        GameRegistry.register(darkGemChestBlock);
        GameRegistry.register(superGemChestBlock);
        GameRegistry.register(darkSuperGemChestBlock);

        GameRegistry.register(gemChangerBlock);
        GameRegistry.register(new ItemBlock(gemChangerBlock).setRegistryName(Gemulation.MODID,Names.GEM_CHANGER));

        GameRegistry.register(gemCraftingTableBlock);
        GameRegistry.register(darkGemCraftingTableBlock);

    }
}
