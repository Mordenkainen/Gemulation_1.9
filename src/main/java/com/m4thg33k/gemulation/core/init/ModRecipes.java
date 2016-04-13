package com.m4thg33k.gemulation.core.init;

import com.m4thg33k.gemulation.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;

public class ModRecipes {

    public static void createRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.gemChangerBlock,1,0),"www","coc","ooo",'w',new ItemStack(Blocks.wool,1, OreDictionary.WILDCARD_VALUE),'o',new ItemStack(Blocks.obsidian,1),'c',new ItemStack(ModItems.chaosOrb,1,1));

        //furnace upgrades
//        for (EnumGem type : EnumGem.values())
//        {
//            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,1,0),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',type.getItemOreName(),'r',"dustRedstone",'l',"gemLapis"));
//            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,2,1),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',type.getItemOreName(),'r',new ItemStack(Items.blaze_powder,1),'l',"gemLapis"));
//            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,2,1),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',type.getItemOreName(),'r',new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedFurnaceBlock,1),'l',"gemLapis"));
//        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,1,0),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',new ItemStack(ModItems.gem,1,OreDictionary.WILDCARD_VALUE),'r',"dustRedstone",'l',"gemLapis"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,2,1),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',new ItemStack(ModItems.gem,1,OreDictionary.WILDCARD_VALUE),'r',new ItemStack(Items.blaze_powder,1),'l',"gemLapis"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(com.m4thg33k.lit.items.ModItems.itemFurnaceUpgrade,2,2),"grg","rcr","glg",'c',ModItems.craftingMaterial.getStack("ChaosCore"),'g',new ItemStack(ModItems.gem,1,OreDictionary.WILDCARD_VALUE),'r',new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedFurnaceBlock,1),'l',"gemLapis"));

    }
}
