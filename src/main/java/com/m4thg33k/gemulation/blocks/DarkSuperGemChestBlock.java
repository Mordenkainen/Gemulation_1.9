package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.api.chest.ChestTypes;
import com.m4thg33k.lit.tiles.TileImprovedChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.silentchaos512.gems.lib.EnumGem;

public class DarkSuperGemChestBlock extends DarkGemChestBlock{

    public DarkSuperGemChestBlock()
    {
        super();

        this.setUnlocalizedName(Names.DARK_SUPER_GEM_CHEST);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileImprovedChest(ChestTypes.getTypeByName("Super " + StringHelper.splitCamelCase(EnumGem.values()[meta+16].getGemName())));
    }
}
