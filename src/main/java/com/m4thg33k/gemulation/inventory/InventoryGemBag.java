package com.m4thg33k.gemulation.inventory;

import com.m4thg33k.gemulation.items.ItemGemBag;
import com.m4thg33k.gemulation.items.ModItems;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

//Heavily based on <Vaskii>'s flower bag

public class InventoryGemBag implements IInventory {

    private static final ItemStack[] DEFAULT_INVENTORY = new ItemStack[32];

    EntityPlayer player;
    int slot;
    ItemStack[] stacks = null;

    boolean inventoryPushed = false;
    ItemStack storedInventory = null;

    public InventoryGemBag(EntityPlayer player, int slot)
    {
        this.player = player;
        this.slot = slot;
    }

    public static boolean isGemBag(ItemStack stack)
    {
        return stack!=null && stack.getItem() == ModItems.itemGemBag;
    }

    ItemStack getStack()
    {
        ItemStack stack = player.inventory.getStackInSlot(slot);;
        if (stack!=null)
        {
            storedInventory = stack;
        }
        return stack;
    }

    ItemStack[] getInventory()
    {
        if (stacks!=null)
        {
            return stacks;
        }

        ItemStack stack = getStack();
        if (isGemBag(getStack()))
        {
            stacks = ItemGemBag.getStacksFromNBT(stack);
            return stacks;
        }

        return DEFAULT_INVENTORY;
    }

    public void pushInventory()
    {
        if (inventoryPushed)
        {
            return;
        }

        ItemStack stack = getStack();
        if (stack==null)
        {
            stack = storedInventory;
        }
        if (stack!=null)
        {
            ItemStack[] inv = getInventory();
            ItemGemBag.setStackNBT(stack,inv);
        }
        inventoryPushed = true;
    }

    @Override
    public int getSizeInventory() {
        return 32;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index<0 || index>=32)
        {
            return null;
        }
        return getInventory()[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack[] inventorySlots = getInventory();
        if (inventorySlots[index]!=null)
        {
            ItemStack stackAt;
            if (inventorySlots[index].stackSize<=count)
            {
                stackAt = inventorySlots[index];
                inventorySlots[index] = null;
                return stackAt;
            }
            else
            {
                stackAt = inventorySlots[index].splitStack(count);

                if (inventorySlots[index].stackSize == 0)
                {
                    inventorySlots[index] = null;
                }
                return stackAt;
            }
        }
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return getStackInSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack[] inventorySlots = getInventory();
        inventorySlots[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return isGemBag(getStack()) ? 64 : 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return isGemBag(getStack());
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return isGemBag(getStack());
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        if (stacks!=null)
        {
            Arrays.fill(stacks,null);
        }
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public String getName() {
        return Names.GEM_BAG;
    }

    @Override
    public void markDirty() {

    }
}
