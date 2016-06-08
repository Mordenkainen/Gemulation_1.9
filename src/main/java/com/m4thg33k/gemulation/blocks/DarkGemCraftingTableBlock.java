package com.m4thg33k.gemulation.blocks;

import com.google.common.collect.Lists;
import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.blocks.ImprovedCraftingTableBlock;
import com.m4thg33k.lit.blocks.ImprovedWorktableBlock;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.silentchaos512.gems.lib.EnumGem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DarkGemCraftingTableBlock extends ImprovedWorktableBlock {

    public static final PropertyEnum<EnumGem> VARIANT = PropertyEnum.create("variant",EnumGem.class, EnumSet.range(EnumGem.CARNELIAN,EnumGem.ALEXANDRITE));

    public DarkGemCraftingTableBlock()
    {
        super();
        this.setUnlocalizedName(Names.DARK_GEM_CRAFTING_TABLE);
        this.setCreativeTab(Gemulation.tabGemulation);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumGem.CARNELIAN));
    }

    @Override
    public void handleRegName() {
        this.setRegistryName(Gemulation.MODID, Names.DARK_GEM_CRAFTING_TABLE);
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
        return this.getDefaultState().withProperty(VARIANT,EnumGem.values()[meta+16]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT).ordinal())-16;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,VARIANT);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> items = Lists.newArrayList();
        items.add(new ItemStack(this,1,getMetaFromState(state)));
        return items;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).ordinal()-16;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }
}
