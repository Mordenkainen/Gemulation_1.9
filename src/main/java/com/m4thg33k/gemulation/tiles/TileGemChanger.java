package com.m4thg33k.gemulation.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.gems.api.energy.IChaosAccepter;

public class TileGemChanger extends TileEntity implements ISidedInventory, ITickable, IChaosAccepter{

    /**
     * slots 0-31 will be input (0-15 light, 16-31 dark)
     * slots 32-63 will be output (32-47 light, 48-63 dark)
     * slot 64 will be the working slot
     * slot 65 will be the target slot
     */

    protected ItemStack[] inventory = new ItemStack[65];
    protected String customName;

    ItemStack target;

    public static final int TIMER_DELAY = 20;
    public static final int WORK_TIME = 100;
    public static final int MAX_ENERGY = 1000000;
    public static final int ENERGY_NEEDED_TO_CHANGE = 500;

    public int timer = 0;

//    public boolean[] blacklisted = new boolean[12];
//    public int numBlacklisted;

    public int workTime;
    public int energyStored;

    public TileGemChanger()
    {
//        resetBlackList();
        target = null;
    }

    @Override
    public int receiveCharge(int i, boolean b) {
        return 0;
    }

    @Override
    public int getCharge() {
        return 0;
    }

    @Override
    public int getMaxCharge() {
        return 0;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
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

    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }
}
