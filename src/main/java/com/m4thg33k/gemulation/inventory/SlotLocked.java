package com.m4thg33k.gemulation.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotLocked extends Slot {

    public SlotLocked(IInventory inventory,int index, int xPos, int yPos)
    {
        super(inventory,index,xPos,yPos);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return false;
    }
}
