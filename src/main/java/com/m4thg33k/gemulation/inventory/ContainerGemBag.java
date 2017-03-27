package com.m4thg33k.gemulation.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGemBag extends Container{

    InventoryGemBag gemBag;
    int slot;

    public ContainerGemBag(EntityPlayer player)
    {
        this.slot = player.inventory.currentItem;

        ItemStack held = player.inventory.getStackInSlot(slot);
        if (held==null)
        {
            slot = ((byte)150); //should be offhand
        }


        IInventory playerInventory = player.inventory;
        gemBag = new InventoryGemBag(player,slot);
        openBag(player);

        //bag inventory (0-31)
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<8;j++)
            {
                this.addSlotToContainer(new SlotGem(gemBag,i*8+j,20+18*j,8+18*i,true,true,i*8+j));
            }
        }

        //player inventory (32-58)
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<9;j++)
            {
                this.addSlotToContainer(new Slot(playerInventory,9+9*i+j,12+18*j,84+18*i));
            }
        }

        //player hotbar (59-67)
        for (int j=0;j<9;j++)
        {
            if (player.inventory.currentItem==j)
            {
                this.addSlotToContainer(new SlotLocked(playerInventory,j,12+18*j,142));
            }
            else
            {
                this.addSlotToContainer(new Slot(playerInventory,j,12+j*18,142));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        boolean can = gemBag.isUsableByPlayer(playerIn);
        if (!can)
        {
            onContainerClosed(playerIn);
        }
        return can;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack previous = null;
        Slot slot = inventorySlots.get(index);

        if (slot!=null && slot.getHasStack())
        {
            ItemStack current = slot.getStack();
            previous = current.copy();

            //inside bag
            if (index<32)
            {
                if (!mergeItemStack(current,32,68,false))
                {
                    return null;
                }
            }
            else if (index>=32 && index<68) //somewhere in player inventory
            {
                if (!mergeItemStack(current,0,32,false))
                {
                    return null;
                }
            }

            if (current.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (current.stackSize == previous.stackSize)
            {
                return null;
            }
            slot.func_82870_a(playerIn,current);
        }
        return previous;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        gemBag.pushInventory();
        closeBag(playerIn);
    }

    private void openBag(EntityPlayer player)
    {
        ItemStack held = player.inventory.getStackInSlot(slot);
        held.setItemDamage((held.getItemDamage()+32)%64);
    }

    private void closeBag(EntityPlayer player)
    {
        ItemStack held = player.inventory.getStackInSlot(slot);
        held.setItemDamage(held.getItemDamage()%32);
    }
}
