package com.m4thg33k.gemulation.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBlacklistExtraction extends Slot {

    private ItemStack[] blacklisted;
    public SlotBlacklistExtraction(IInventory inventory, int index, int xPos, int yPos, ItemStack... blacklisted)
    {
        super(inventory,index,xPos,yPos);

        this.blacklisted = blacklisted;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        ItemStack inside = this.getStack();
        for (ItemStack blacklist : blacklisted)
        {
            if (ItemStack.areItemsEqual(inside,blacklist))
            {
                return false;
            }
        }
        return true;
    }
}
