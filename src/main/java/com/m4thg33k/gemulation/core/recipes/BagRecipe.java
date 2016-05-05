package com.m4thg33k.gemulation.core.recipes;

import com.m4thg33k.gemulation.items.ItemGemBag;
import com.m4thg33k.gemulation.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.silentchaos512.gems.block.ModBlocks;

import java.util.ArrayList;

public class BagRecipe implements IRecipe{


    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean foundBag =false;
        boolean foundRose = false;
        boolean foundNetherrack = false;
        for (int i=0;i<inv.getSizeInventory();i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack!=null)
            {
                if (foundBag && foundRose && foundNetherrack)
                {
                    return false;
                }
                if (stack.getItem() instanceof ItemGemBag)
                {
                    foundBag = true;
                }
                else if (stack.getItem() == Item.getItemFromBlock(ModBlocks.glowRose))
                {
                    foundRose = true;
                }
                else if (stack.getItem() == Item.getItemFromBlock(Blocks.netherrack))
                {
                    foundNetherrack = true;
                }
                else
                {
                    return false;
                }
            }
        }
        return foundBag&&foundRose;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack bag = null;
        int roseMeta = 0;
        boolean netherrack = false;

        for (int i=0;i<inv.getSizeInventory();i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack!=null)
            {
                if (stack.getItem() instanceof ItemGemBag)
                {
                    bag = stack.copy();
                }
                else if (stack.getItem() == Item.getItemFromBlock(ModBlocks.glowRose))
                {
                    roseMeta = stack.getItemDamage();
                }
                else if (stack.getItem() == Item.getItemFromBlock(Blocks.netherrack))
                {
                    netherrack = true;
                }
            }
        }
        NBTTagCompound tagCompound = null;
        if (bag.hasTagCompound())
        {
            tagCompound = bag.getTagCompound();
        }
        ItemStack output = new ItemStack(ModItems.itemGemBag,1,roseMeta+(netherrack?16:0));

        if (tagCompound!=null)
        {
            output.setTagCompound(tagCompound);
        }
//        if (tagCompound!=null)
//        {
//            output = new ItemStack(ModItems.itemGemBag,1,roseMeta+(netherrack?16:0),tagCompound);
//        }
//        else
//        {
//            output = new ItemStack(ModItems.itemGemBag,1,roseMeta+(netherrack?16:0));
//        }

        return output;

    }

    @Override
    public int getRecipeSize() {
        return 3;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(ModItems.itemGemBag,1,0);
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        return new ItemStack[inv.getSizeInventory()];
    }
}
