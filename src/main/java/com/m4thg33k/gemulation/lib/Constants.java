package com.m4thg33k.gemulation.lib;

import com.m4thg33k.gemulation.core.util.LogHelper;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import net.minecraft.item.Item;
import net.silentchaos512.gems.lib.EnumGem;

public class Constants {

    public static final int numStats = 5;
    public static final double[] stats = new double[5*EnumGem.values().length];

    //Furnace Upgrades
    public static final double SPEED_MULT = 0.25;
    public static final double FUEL_MULT = 1.25;
    public static final double CAP_MULT = 1.25;

    public static void getFurnaceGemStats()
    {
        for (EnumGem type : EnumGem.values())
        {
            int row = type.ordinal()*numStats;

            stats[row] = baseCookTime(type); //column 0
            stats[row+1] = baseFuelBoost(type); //column 1
            stats[row+2] = upgradeCount(type); //column 2
            stats[row+3] = baseMaxFuel(type,stats[row+1]);
            stats[row+4] = stats[row+3]/(1600*64*stats[row+1]);
        }

        LogHelper.info("Finished creating furnace stat array");
    }

    public static double getCookFactor(EnumGem type)
    {
        return stats[type.ordinal()*numStats];
    }

    public static double getFuelBoost(EnumGem type)
    {
        return Math.round(stats[type.ordinal()*numStats+1]);
    }

    public static int getUpgradeCount(EnumGem type)
    {
        return (int)stats[type.ordinal()*numStats+2];
    }

    public static int getFuelCap(EnumGem type)
    {
        return (int)stats[type.ordinal()*numStats+3];
    }

    public static double getMaxStacksFuel(EnumGem type)
    {
        return (Math.floor(100*stats[type.ordinal()*numStats+4]))*0.01;
    }

    public static double slope(double x1, double x2, double y1, double y2)
    {
        return (y1-y2)/(x1-x2);
    }


    public static double utilizeMagicFunction(double x1, double x2, double y1, double y2,double value)
    {
        double m = slope(x1,x2,y1,y2);
        double b = y2 - m*x2;
        return m*value+b;
    }

    //material functions
    public static double baseCookTime(EnumGem type)
    {
        return 1.0/utilizeMagicFunction(11,5,2,1.0/1.125,type.getMiningSpeed(null));
    }
    public static double baseFuelBoost(EnumGem type)
    {
        return utilizeMagicFunction(4.5,2,2,1,(type.getMeleeDamage(null)+type.getMagicDamage(null))/2.0);
    }
    public static double upgradeCount(EnumGem type)
    {
        return Math.floor(utilizeMagicFunction(15,8,4,0,type.getEnchantability(null)));
    }
    public static double baseMaxFuel(EnumGem type,double factor)
    {
        return utilizeMagicFunction(768,128,204800,51200,type.getDurability(null))*factor;
    }
}
