package com.m4thg33k.gemulation.core.init;

import com.m4thg33k.gemulation.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;

public class ModRecipes {

    public static void createRecipes()
    {
//        for (EnumGem type : EnumGem.values())
//        {
//            GameRegistry.addRecipe(new ItemStack((type.ordinal()<16? ModBlocks.gemFurnaceBlock:ModBlocks.darkGemFurnaceBlock),1,type.ordinal()%16)," m "," f "," m ",'m',new ItemStack(ModItems.gem,1,type.ordinal()),'f', new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedFurnaceBlock,1));
//            GameRegistry.addRecipe(new ItemStack((type.ordinal()<16?ModBlocks.gemChestBlock:ModBlocks.darkGemChestBlock),1,type.ordinal()%16),"mmm","mcm","mmm",'m',new ItemStack(ModItems.gem,1,type.ordinal()),'c',new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedChestBlock,1));
//        }
    }
}
