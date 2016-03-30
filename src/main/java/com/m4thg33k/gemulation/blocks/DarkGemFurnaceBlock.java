package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.api.LitStateProps;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import com.m4thg33k.lit.blocks.ImprovedFurnaceBlock;
import com.m4thg33k.lit.tiles.TileImprovedFurnace;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.silentchaos512.gems.lib.EnumGem;

import java.util.EnumSet;
import java.util.List;

public class DarkGemFurnaceBlock extends ImprovedFurnaceBlock{

    public static final PropertyEnum<EnumGem> VARIANT = PropertyEnum.create("variant",EnumGem.class, EnumSet.range(EnumGem.CARNELIAN, EnumGem.ALEXANDRITE));

    public DarkGemFurnaceBlock()
    {
        super();

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumGem.CARNELIAN).withProperty(LitStateProps.CARDINALS, EnumFacing.NORTH).withProperty(ON,false));
        this.setUnlocalizedName(Names.DARK_GEM_FURNACE);
        this.setCreativeTab(Gemulation.tabGemulation);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,LitStateProps.CARDINALS,ON,VARIANT);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i=0;i<16;i++)
        {
            list.add(new ItemStack(itemIn,1,i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT,EnumGem.values()[meta+16]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT)).ordinal()%16;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileImprovedFurnace tileImprovedFurnace = (TileImprovedFurnace)worldIn.getTileEntity(pos);
        return state.withProperty(ON,tileImprovedFurnace.getOn()).withProperty(LitStateProps.CARDINALS,tileImprovedFurnace.getFacing());
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileImprovedFurnace(FurnaceTypes.getTypeByName(StringHelper.splitCamelCase(state.getValue(VARIANT).getGemName())));
    }
}
