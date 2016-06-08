package com.m4thg33k.gemulation.tiles;

import com.m4thg33k.gemulation.core.util.LogHelper;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.silentchaos512.gems.api.energy.IChaosAccepter;
import net.silentchaos512.gems.item.ModItems;

import javax.annotation.Nullable;

public class TileGemChanger extends TileEntity implements ISidedInventory, ITickable, IChaosAccepter{

    /**
     * slots 0-31 will be input (0-15 light, 16-31 dark)
     * slots 32-63 will be output (32-47 light, 48-63 dark)
     * slot 64 will be the working slot
     * slot 65 will be the target slot
     */

    protected ItemStack[] inventory = new ItemStack[66];
    protected String customName;

    ItemStack target;

    public static final int TIMER_DELAY = 20;
    public static final int WORK_TIME = 100;
    public static final int MAX_ENERGY = 1000000;
    public static final int ENERGY_NEEDED_TO_CHANGE = 2000;
    public static final int[] AUTOMATION_FACES = new int[64];
    public static final int MAX_CHANGES = 40;


    public int timer = 0;

//    public boolean[] blacklisted = new boolean[12];
//    public int numBlacklisted;

    public int workTime;
    public int energyStored;
    public int numChanges;

    public TileGemChanger()
    {
        for (int i=0;i<64;i++)
        {
            AUTOMATION_FACES[i] = i;
        }
//        resetBlackList();
        target = null;
    }

    @Override
    public int receiveCharge(int amount, boolean simulate) {
        int amountReceived;
        if (energyStored + amount > getMaxCharge())
        {
            amountReceived = getMaxCharge() - energyStored;
        }
        else
        {
            amountReceived = amount;
        }

        if (!simulate)
        {
            energyStored += amountReceived;
            this.worldObj.markAndNotifyBlock(pos,null,worldObj.getBlockState(pos),worldObj.getBlockState(pos),1);
            //LogHelper.info("I received " + amountReceived + " energy and now have: " + energyStored);
        }

        return amountReceived;

    }

    @Override
    public int getCharge() {
        return energyStored;
    }

    @Override
    public int getMaxCharge() {
        return MAX_ENERGY;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList list = compound.getTagList("Items",10);
        for (int i=0;i<list.tagCount();i++)
        {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getInteger("Slot");
            setInventorySlotContents(slot,ItemStack.loadItemStackFromNBT(stackTag));
        }

        customName = compound.getString("CustomName");

        timer = compound.getInteger("Timer");

        workTime = compound.getInteger("WorkTime");
        energyStored = compound.getInteger("EnergyStored");
        target = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("TargetStack"));
        numChanges = compound.getInteger("NumChanges");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList list = new NBTTagList();
        for (int i=0;i<getSizeInventory();i++)
        {
            if (inventory[i]!=null)
            {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setInteger("Slot",i);
                inventory[i].writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        compound.setTag("Items",list);

        if (customName!=null && !customName.equals("")) {
            compound.setString("CustomName", customName);
        }

        compound.setInteger("Timer",timer);

        compound.setInteger("WorkTime",workTime);
        compound.setInteger("EnergyStored", energyStored);

        NBTTagCompound targetStack = new NBTTagCompound();
        if (target!=null)
        {
            target.writeToNBT(targetStack);
        }
        compound.setTag("TargetStack",targetStack);
        compound.setInteger("NumChanges",numChanges);

        return compound;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return AUTOMATION_FACES;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return (index>=0 && index<32 && isItemValidForSlot(index,itemStackIn));
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return (index>=32 && index<64);
    }

    @Override
    public int getSizeInventory() {
        return 66;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index<0 || index>=this.getSizeInventory())
        {
            return null;
        }
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index)!=null)
        {
            ItemStack itemStack;
            if (getStackInSlot(index).stackSize<=count)
            {
                itemStack = getStackInSlot(index);
                setInventorySlotContents(index,null);
                markDirty();
                return itemStack;
            }
            itemStack = this.getStackInSlot(index).splitStack(count);

            if (getStackInSlot(index).stackSize <=0 )
            {
                setInventorySlotContents(index,null);
            }
            else
            {
                //just to show that changes happened
                setInventorySlotContents(index,getStackInSlot(index));
            }

            markDirty();

            return itemStack;
        }

        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index<=0 || index>=getSizeInventory())
        {
            return null;
        }

        ItemStack stack = inventory[index];
        inventory[index] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index] = stack;

        if (stack!=null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(pos) == this && player.getDistanceSq(pos.add(0.5,0.5,0.5))<=64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return !(index<0 || index>=32) && (stack.getItem() == ModItems.gem && index==stack.getItemDamage());
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
        for (int i=0;i<getSizeInventory();i++)
        {
            setInventorySlotContents(i,null);
        }
    }


    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container." + Names.GEM_CHANGER;
    }

    @Override
    public boolean hasCustomName() {
        return customName!=null && !customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }


    @Override
    public void update() {
        boolean isDirty = false;
        boolean wasMoved;

        increaseTimer();

        if (!worldObj.isRemote)
        {

            isDirty = moveToWork();

            if (hasWork() &&!needsToStart())
            {
                workTime--;
            }

            if (needsToStart() && hasEnoughEnergy())
            {
                energyStored -= ENERGY_NEEDED_TO_CHANGE;
                workTime--;
            }

            if (hasRoomForOutput() && !hasWork())
            {
                convertGem();
                wasMoved = moveToOutput();
                if (!wasMoved)
                {
                    workTime = WORK_TIME;
                }
                isDirty = true;
            }
        }

        if (isDirty)
        {
            markDirty();
        }
    }

    public ItemStack getTarget()
    {
        return inventory[65];
    }

    public ItemStack getWorking()
    {
        return inventory[64];
    }

    public boolean isTargetDark()
    {
        if (getTarget()==null)
        {
            return false;
        }
        return (getTarget().getItemDamage()>=16 && getTarget().getItemDamage()<32);
    }

    /**
     * attempts to move a gem from the input slot into the working area
     * it will not pull from the same slot as the target gem (if one exists)
     * it will not pull a dark gem if the target is light (and vise-versa)
     * returns true if it succeeds, false if it doesn't
     */
    public boolean moveToWork()
    {
        ItemStack working = getWorking();
        if (working != null)
        {
            return false; //already has a gem there
        }
        ItemStack target = getTarget();
        if (target!=null)
        {
            int start = 0;
            int end = 16;
            if (isTargetDark())
            {
                start += 16;
                end += 16;
            }
            int skip = target.getItemDamage();
            for (int i=start;i<end;i++)
            {
                if (i==skip)
                {
                    continue;
                }
                if (inventory[i]!=null)
                {
                    setInventorySlotContents(64,new ItemStack(inventory[i].getItem(),1,inventory[i].getItemDamage()));
                    decrStackSize(i,1);
                    workTime = WORK_TIME;
                    numChanges = 0;
                    return true;
                }
            }
        }
        else
        {
            for (int i=0;i<32;i++)
            {
                if (inventory[i]!=null)
                {
                    setInventorySlotContents(64,new ItemStack(inventory[i].getItem(),1,inventory[i].getItemDamage()));
                    decrStackSize(i,1);
                    workTime = WORK_TIME;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * moves the gem from the working slot to its corresponding output slot
     * if there is a target gem and the gems don't match, this returns false
     * it returns true if a gem is actually moved
     *
     * this should only be called after the appropriate checks have been made
     * to ensure there is room for the gem
     */
    public boolean moveToOutput()
    {
        ItemStack working = getWorking();
        if (working==null)
        {
            return false;
        }
        ItemStack target = getTarget();
        if (target!=null && working.getItemDamage()!=target.getItemDamage())
        {
            return false;
        }

        int index = working.getItemDamage()+32;
        if (inventory[index]==null)
        {
            setInventorySlotContents(index,new ItemStack(working.getItem(),1,working.getItemDamage()));
        }
        else
        {
            inventory[index].stackSize++;
        }
        decrStackSize(64,1);
        return true;
    }

    /**
     * checks if there is room to place a converted gem. If there is no target gem, it checks if
     * all output slots have room for a gem (since it's randomized). Otherwise, it only checks the
     * target gem's location. If there is no gem in the working location, this automatically
     * returns false.
     */
    public boolean hasRoomForOutput()
    {
        ItemStack working = getWorking();
        if (working==null)
        {
            return false;
        }

        ItemStack target = getTarget();
        if (target==null)
        {
            //check every location corresponding to the type of gem
            int start = 0;
            int end = 16;
            if (working.getItemDamage()>=16)
            {
                start += 16;
                end += 16;
            }
            for (int i=32+start;i<32+end;i++)
            {
                if (inventory[i]!=null && inventory[i].stackSize==getInventoryStackLimit())
                {
                    return false;
                }
            }
            return true;
        }

        //otherwise just check the target's output location
        int targetIndex = 32 + target.getItemDamage();
        return !(inventory[targetIndex]!=null && inventory[targetIndex].stackSize==getInventoryStackLimit());
    }

    /**
     * Converts a gem in the working area to another gem and leaves it there.
     * This process is completely random (apart from the fact that it won't convert a gem
     * to the exact same type and it will never convert between light and dark gems)
     * This block no longer keeps track of previous types the gem has been converted to. Instead, if it
     * converts the same gem into 40 other gems (in a row), the 41st conversion will produce the desired target.
     * This still allows for randomness, so you can get lucky with the conversions, but also guarantees that you will
     * (eventually) get the target gem...but it could be costly!
     */
    public void convertGem()
    {
        ItemStack working = getWorking();
        if (working==null)
        {
            return;
        }

        int newType = worldObj.rand.nextInt(15);
        if (getTarget()!=null && numChanges>=MAX_CHANGES)
        {
            newType = getTarget().getItemDamage();
        }
        else
        {
            if (working.getItemDamage()>=16)
            {
                newType += 16;
            }
            if (newType>=working.getItemDamage())
            {
                newType += 1;
            }
        }

        setInventorySlotContents(64,new ItemStack(ModItems.gem,1,newType));
        numChanges++;
    }

    public void increaseTimer()
    {
        timer = (timer+1)%TIMER_DELAY;
    }

    public boolean hasWork()
    {
        return workTime>0;
    }

    public boolean hasEnoughEnergy()
    {
        return energyStored >= ENERGY_NEEDED_TO_CHANGE;
    }

    public boolean needsToStart()
    {
        return workTime==WORK_TIME;
    }



















}
