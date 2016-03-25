package com.m4thg33k.gemulation.blocks;

import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.api.LitStateProps;
import com.m4thg33k.lit.api.furnace.FurnaceTypes;
import com.m4thg33k.lit.blocks.ImprovedFurnaceBlock;
import com.m4thg33k.lit.tiles.TileImprovedFurnace;
import net.minecraft.block.properties.PropertyBool;
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

import java.util.List;

public class GemFurnaceBlock extends ImprovedFurnaceBlock {

    private boolean isBlockDark;

    public static final PropertyEnum<EnumGem> VARIANT = PropertyEnum.create("variant",EnumGem.class);
    public static final PropertyBool IS_DARK = PropertyBool.create("dark");

    public GemFurnaceBlock(boolean dark)
    {
        super();
        this.isBlockDark = dark;

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumGem.RUBY).withProperty(LitStateProps.CARDINALS, EnumFacing.NORTH).withProperty(ON,false).withProperty(IS_DARK,isBlockDark));
        this.setUnlocalizedName(Names.GEM_FURNACE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LitStateProps.CARDINALS,ON,VARIANT,IS_DARK);
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
        return getDefaultState().withProperty(VARIANT, EnumGem.values()[meta+(isBlockDark?16:0)]).withProperty(IS_DARK,isBlockDark);
//        return getDefaultState().withProperty(VARIANT, isBlockDark ? EnumGem.values()[meta+16] : EnumGem.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        isBlockDark = state.getValue(IS_DARK);
        return (state.getValue(VARIANT).ordinal())%16;
//        LogHelper.info("Getting meta from state and returning: " + state.getValue(VARIANT).name() + " and " + ((state.getValue(VARIANT).ordinal()) - (isBlockDark ?16:0)));
//        if (((state.getValue(VARIANT).ordinal()) - (isBlockDark?16:0))<0)
//        {
//            LogHelper.error("DANGER!");
//        }
//        return (state.getValue(VARIANT).ordinal()) - (isBlockDark?16:0);
//        return (state.getValue(VARIANT).ordinal()) + (isBlockDark ?16:0);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileImprovedFurnace tileImprovedFurnace = (TileImprovedFurnace)worldIn.getTileEntity(pos);
        return state.withProperty(ON,tileImprovedFurnace.getOn()).withProperty(LitStateProps.CARDINALS,tileImprovedFurnace.getFacing()).withProperty(IS_DARK,isBlockDark);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileImprovedFurnace(FurnaceTypes.getTypeByName(StringHelper.splitCamelCase(state.getValue(VARIANT).getGemName())));
    }

    @Override
    public String getUnlocalizedName() {
        return super.getUnlocalizedName() + (isBlockDark ?"_dark":"");
    }


}
