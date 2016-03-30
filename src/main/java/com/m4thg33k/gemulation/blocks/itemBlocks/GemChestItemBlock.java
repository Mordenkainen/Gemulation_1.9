package com.m4thg33k.gemulation.blocks.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GemChestItemBlock extends ItemBlock {

    public GemChestItemBlock(Block block)
    {
        super(block);

        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage<32?damage:0;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + stack.getItemDamage();
//        return "tile.gemulation:"+ EnumGem.values()[stack.getItemDamage()].getGemName() + "_chest";
    }
}
