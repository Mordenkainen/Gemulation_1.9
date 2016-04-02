package com.m4thg33k.gemulation.tiles;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
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


}
