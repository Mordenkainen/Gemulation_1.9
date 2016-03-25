package com.m4thg33k.gemulation.core.init;

import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Constants;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import com.m4thg33k.lit.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;

public class MiscInits {

    public static void addFurnaceTypes()
    {
        Constants.getFurnaceGemStats();

        for (EnumGem type : EnumGem.values())
        {
            FurnaceTypes.addType(StringHelper.splitCamelCase(type.getGemName()),Constants.getCookFactor(type),Constants.getUpgradeCount(type),Constants.baseFuelBoost(type),Constants.getFuelCap(type),new ItemStack(ModBlocks.improvedFurnaceBlock),new ItemStack(ModItems.gem,1,type.ordinal()));
        }
    }


}
