package com.m4thg33k.gemulation.core.init;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.blocks.ModBlocks;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Constants;
import com.m4thg33k.lit.api.chest.ChestTypes;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;

import java.util.EnumSet;

public class MiscInits {

    public static void addFurnaceTypes()
    {
        Constants.getFurnaceGemStats();

        for (EnumGem type : EnumSet.range(EnumGem.RUBY,EnumGem.OPAL))
        {
            FurnaceTypes.addType(StringHelper.splitCamelCase(type.getGemName()),Constants.getCookFactor(type),Constants.getUpgradeCount(type),Constants.baseFuelBoost(type),Constants.getFuelCap(type),new ItemStack(ModBlocks.gemFurnaceBlock,1,type.ordinal()),new ItemStack(ModItems.gem,1,type.ordinal()));
        }
        for (EnumGem type : EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE))
        {
            FurnaceTypes.addType(StringHelper.splitCamelCase(type.getGemName()),Constants.getCookFactor(type),Constants.getUpgradeCount(type),Constants.baseFuelBoost(type),Constants.getFuelCap(type),new ItemStack(ModBlocks.darkGemFurnaceBlock,1,type.ordinal()%16),new ItemStack(ModItems.gem,1,type.ordinal()));
        }
    }

    public static void addChestTypes()
    {
        for (EnumGem type : EnumSet.range(EnumGem.RUBY, EnumGem.OPAL))
        {
            ChestTypes.addType(StringHelper.splitCamelCase(type.getGemName()),54,false,new ResourceLocation(Gemulation.MODID,"textures/gui/BasicGemChest.png"),184,202,6,9,new ResourceLocation(Gemulation.MODID,"textures/model/GemChest" + type.ordinal() + ".png"),new ItemStack(ModItems.gem,1,type.ordinal()),new ItemStack(ModBlocks.gemChestBlock,1,type.ordinal()),false);
        }
        for (EnumGem type : EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE))
        {
            ChestTypes.addType(StringHelper.splitCamelCase(type.getGemName()),54,false,new ResourceLocation(Gemulation.MODID,"textures/gui/BasicGemChest.png"),187,202,6,9,new ResourceLocation(Gemulation.MODID,"textures/model/GemChestDark" + (type.ordinal()-16) + ".png"),new ItemStack(ModItems.gem,1,type.ordinal()),new ItemStack(ModBlocks.darkGemChestBlock,1,type.ordinal()-16),false);
        }
    }


}
