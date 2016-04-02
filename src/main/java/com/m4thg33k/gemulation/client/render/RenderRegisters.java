package com.m4thg33k.gemulation.client.render;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.blocks.ModBlocks;
import com.m4thg33k.gemulation.core.util.LogHelper;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.silentchaos512.gems.lib.EnumGem;

public class RenderRegisters {

    public static void preInit()
    {
        RenderRegisters.registerItemRenders();
    }

    private static void registerItemRenders()
    {
        //itemblocks
        for (EnumGem type : EnumGem.values())
        {
            int ord = type.ordinal();
            if (ord<16) //isn't dark
            {
//                LogHelper.info("Registering render for: " + type.name() + " with ordinal " + ord + " and meta " + ord);
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.gemFurnaceBlock),ord,new ModelResourceLocation(Gemulation.MODID+":"+Names.GEM_FURNACE,"facing=north,on=false,variant="+type.name()));
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.gemChestBlock),ord,new ModelResourceLocation(Gemulation.MODID+":"+Names.GEM_CHEST,"variant="+type.name()));
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.superGemChestBlock),ord,new ModelResourceLocation(Gemulation.MODID+":"+Names.SUPER_GEM_CHEST,"variant="+type.name()));
            }
            else
            {
//                LogHelper.info("Registering render for: " + type.name() + " with ordinal " + ord + " and meta " + (ord-16));
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.darkGemFurnaceBlock),ord-16,new ModelResourceLocation(Gemulation.MODID+":"+Names.GEM_FURNACE,"facing=north,on=false,variant="+type.name()));
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.darkGemChestBlock),ord-16,new ModelResourceLocation(Gemulation.MODID+":"+Names.GEM_CHEST,"variant="+type.name()));
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.darkSuperGemChestBlock),ord-16,new ModelResourceLocation(Gemulation.MODID+":"+Names.DARK_SUPER_GEM_CHEST,"variant="+type.name()));
            }
//            boolean isDark = type.ordinal()<16;
//            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(isDark? ModBlocks.gemFurnaceBlock:ModBlocks.darkGemFurnaceBlock),isDark?type.ordinal()-16:type.ordinal(),new ModelResourceLocation(Gemulation.MODID+":"+ Names.GEM_FURNACE,"facing=north,isDark="+isDark+",on=false,variant="+ type.name()));
        }
    }
}
