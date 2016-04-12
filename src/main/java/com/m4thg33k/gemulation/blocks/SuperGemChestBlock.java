package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.api.chest.ChestTypes;
import com.m4thg33k.lit.tiles.TileImprovedChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.silentchaos512.gems.lib.EnumGem;

public class SuperGemChestBlock extends GemChestBlock {

    public SuperGemChestBlock()
    {
        super();

        this.setUnlocalizedName(Names.SUPER_GEM_CHEST);

        //this.setRegistryName(Gemulation.MODID,Names.SUPER_GEM_CHEST);
    }

    @Override
    protected void handleRegName() {
        this.setRegistryName(Gemulation.MODID,Names.SUPER_GEM_CHEST);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileImprovedChest(ChestTypes.getTypeByName("Super "+ StringHelper.splitCamelCase(EnumGem.values()[meta].getGemName())));
    }


}
