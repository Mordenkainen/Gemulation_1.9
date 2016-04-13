package com.m4thg33k.gemulation.inventory;

import com.m4thg33k.gemulation.tiles.TileGemChanger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGemChanger extends Container {

    private TileGemChanger te;

    public int workValue = 0;
    public int storedEnergy = 0;

    public ContainerGemChanger(InventoryPlayer playerInventory, TileGemChanger tileGemChanger)
    {
        this.te = tileGemChanger;

        //te inventory
        //light gem input 0-15
        for (int x=0;x<2;x++)
        {
            for (int y=0;y<8;y++)
            {
                this.addSlotToContainer(new SlotGem(te,x+2*y,8+18*x,8+18*y,true,true,x+y*2));
            }
        }
        //dark gem input 16-31
        for (int x=0;x<2;x++)
        {
            for (int y=0;y<8;y++)
            {
                this.addSlotToContainer(new SlotGem(te,x+2*y+16,44+18*x,8+18*y,true,true,x+y*2+16));
            }
        }
        //light gem output 32-47
        for (int x=0;x<2;x++)
        {
            for (int y=0;y<8;y++)
            {
                this.addSlotToContainer(new SlotGem(te,y*2+x+32,250+18*x,8+18*y,false,true,x+y*2));
            }
        }
        //dark gem output 48-63
        for (int x=0;x<2;x++)
        {
            for (int y=0;y<8;y++)
            {
                this.addSlotToContainer(new SlotGem(te,y*2+x+48,286+18*x,8+18*y,false,true,x+y*2+16));
            }
        }

        //working slot 64
        this.addSlotToContainer(new SlotVariableInput(te,64,156,44,false,false));

        //target slot 65
        this.addSlotToContainer(new SlotGemChangerTarget(te,65,156,26));

        //player inventory (slots 66-92; ids 9-35)
        for (int y=0;y<3;y++)
        {
            for (int x=0;x<9;x++)
            {
                addSlotToContainer(new Slot(playerInventory,x+y*9+9,84+x*18,84+y*18));
            }
        }

        //player hotbar (slots 93-101; ids 0-8)
        for (int x=0;x<9;x++)
        {
            addSlotToContainer(new Slot(playerInventory,x,84+x*18,142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack previous = null;
        Slot slot = inventorySlots.get(index);

        if (slot!=null && slot.getHasStack())
        {
            ItemStack current = slot.getStack();
            previous = current.copy();

            //insert custom behaviour
            if (index<64 || index == 65)
            {
                //moving from te input/output and target slots
                if(!mergeItemStack(current,66,102,true))
                {
                    return null;
                }

            }
            else if (index>65)
            {
                //moving from player inventory
                if (!mergeItemStack(current,0,32,false))
                {
                    return null;
                }
            }
            //end custom behaviour

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
            slot.onPickupFromSlot(playerIn,current);
        }
        return previous;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i=0;i<this.crafters.size();i++)
        {
            ICrafting iCrafting = this.crafters.get(i);

            if (this.workValue != this.te.workTime)
            {
                iCrafting.sendProgressBarUpdate(this,0,this.te.workTime);
            }

            if (this.storedEnergy != this.te.getCharge())
            {
                iCrafting.sendProgressBarUpdate(this,1,this.te.getCharge()%1000);
                iCrafting.sendProgressBarUpdate(this,2,this.te.getCharge()/1000);
            }
        }

        this.workValue = this.te.workTime;
        this.storedEnergy = this.te.getCharge();
    }

    @Override
    public void onCraftGuiOpened(ICrafting iCrafting) {
        super.onCraftGuiOpened(iCrafting);

        iCrafting.sendProgressBarUpdate(this,0,workValue);
        iCrafting.sendProgressBarUpdate(this,1,this.te.getCharge()%1000);
        iCrafting.sendProgressBarUpdate(this,2,this.te.getCharge()/1000);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        switch (id)
        {
            case 0:
                workValue = data;
                break;
            case 1:
                storedEnergy = data;
                break;
            case 2:
                storedEnergy += data*1000;
                break;
            default:
        }
    }
}
