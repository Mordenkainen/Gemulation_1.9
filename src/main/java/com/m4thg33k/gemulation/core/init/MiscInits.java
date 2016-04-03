package com.m4thg33k.gemulation.core.init;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.blocks.ModBlocks;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Constants;
import com.m4thg33k.lit.api.chest.ChestTypes;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;

import java.util.EnumSet;

public class MiscInits {

    public static void addFurnaceTypes()
    {
        Constants.getFurnaceGemStats();

        for (EnumGem type : EnumSet.range(EnumGem.RUBY,EnumGem.OPAL))
        {
            FurnaceTypes.addType(StringHelper.splitCamelCase(type.getGemName()),Constants.getCookFactor(type),Constants.getUpgradeCount(type),Constants.baseFuelBoost(type),Constants.getFuelCap(type),new ItemStack(ModBlocks.gemFurnaceBlock,1,type.ordinal()),type.getItem(),false,new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedFurnaceBlock,1),new ItemStack(ModBlocks.gemFurnaceBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemFurnaceBlock,1,OreDictionary.WILDCARD_VALUE));
        }
        for (EnumGem type : EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE))
        {
            FurnaceTypes.addType(StringHelper.splitCamelCase(type.getGemName()),Constants.getCookFactor(type),Constants.getUpgradeCount(type),Constants.baseFuelBoost(type),Constants.getFuelCap(type),new ItemStack(ModBlocks.darkGemFurnaceBlock,1,type.ordinal()%16),type.getItem(),false,new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedFurnaceBlock),new ItemStack(ModBlocks.gemFurnaceBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemFurnaceBlock,1,OreDictionary.WILDCARD_VALUE));
        }
    }

    public static void addChestTypes()
    {
        //standard
        for (EnumGem type : EnumSet.range(EnumGem.RUBY, EnumGem.OPAL))
        {
            ChestTypes.addType(StringHelper.splitCamelCase(type.getGemName()),54,false,new ResourceLocation(Gemulation.MODID,"textures/gui/BasicGemChest.png"),184,202,6,9,new ResourceLocation(Gemulation.MODID,"textures/model/GemChest" + type.ordinal() + ".png"),type.getItem(),new ItemStack(ModBlocks.gemChestBlock,1,type.ordinal()),false,false,new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedChestBlock),new ItemStack(ModBlocks.gemChestBlock,1, OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemChestBlock,1, OreDictionary.WILDCARD_VALUE));
        }
        for (EnumGem type : EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE))
        {
            ChestTypes.addType(StringHelper.splitCamelCase(type.getGemName()),54,false,new ResourceLocation(Gemulation.MODID,"textures/gui/BasicGemChest.png"),187,202,6,9,new ResourceLocation(Gemulation.MODID,"textures/model/GemChestDark" + (type.ordinal()-16) + ".png"),type.getItem(),new ItemStack(ModBlocks.darkGemChestBlock,1,type.ordinal()-16),false,false,new ItemStack(com.m4thg33k.lit.blocks.ModBlocks.improvedChestBlock),new ItemStack(ModBlocks.gemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemChestBlock,1, OreDictionary.WILDCARD_VALUE));
        }

        //supercharged
        for (EnumGem type : EnumSet.range(EnumGem.RUBY,EnumGem.OPAL))
        {
            ChestTypes.addType("Super " + StringHelper.splitCamelCase(type.getGemName()),108,true,new ResourceLocation(Gemulation.MODID,"textures/gui/SuperGemChest.png"),238,256,9,12,new ResourceLocation(Gemulation.MODID,"textures/model/SuperGemChest" + type.ordinal() + ".png"),type.getItemSuper(),new ItemStack(ModBlocks.superGemChestBlock,1,type.ordinal()),true,true,new ItemStack(ModBlocks.gemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.superGemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkSuperGemChestBlock,1,OreDictionary.WILDCARD_VALUE));
        }
        for (EnumGem type : EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE))
        {
            ChestTypes.addType("Super " + StringHelper.splitCamelCase(type.getGemName()),108,true,new ResourceLocation(Gemulation.MODID,"textures/gui/SuperGemChest.png"),238,256,9,12,new ResourceLocation(Gemulation.MODID,"textures/model/SuperGemChestDark" + (type.ordinal()-16) + ".png"),type.getItemSuper(),new ItemStack(ModBlocks.darkSuperGemChestBlock,1,type.ordinal()-16),true,true,new ItemStack(ModBlocks.gemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkGemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.superGemChestBlock,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModBlocks.darkSuperGemChestBlock,1,OreDictionary.WILDCARD_VALUE));
        }
    }


}
