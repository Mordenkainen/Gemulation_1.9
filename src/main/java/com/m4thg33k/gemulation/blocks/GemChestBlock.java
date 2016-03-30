package com.m4thg33k.gemulation.blocks;

import com.google.common.collect.Lists;
import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.gui.GemulationGuiHandler;
import com.m4thg33k.gemulation.lib.Names;
import com.m4thg33k.lit.api.chest.ChestTypes;
import com.m4thg33k.lit.blocks.ImprovedChestBlock;
import com.m4thg33k.lit.tiles.TileImprovedChest;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.silentchaos512.gems.lib.EnumGem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GemChestBlock extends ImprovedChestBlock {

    public static final PropertyEnum<EnumGem> VARIANT = PropertyEnum.create("variant",EnumGem.class, EnumSet.range(EnumGem.RUBY,EnumGem.OPAL));

    public GemChestBlock()
    {
        super();

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,EnumGem.RUBY));
        this.setUnlocalizedName(Names.GEM_CHEST);
        this.setCreativeTab(Gemulation.tabGemulation);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity te = worldIn.getTileEntity(pos);

//        LogHelper.info("**Facing is: " + ((TileImprovedChest)te).getFacing());

        if (worldIn.isRemote || worldIn.isSideSolid(pos.add(0,1,0),EnumFacing.DOWN))
        {
            return true;
        }
        playerIn.openGui(Gemulation.instance, GemulationGuiHandler.GEM_CHEST_GUI,worldIn,pos.getX(),pos.getY(),pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
//        String data = StringHelper.splitCamelCase(EnumGem.values()[meta].getGemName());
//        LogHelper.info(data);
        return new TileImprovedChest(ChestTypes.getTypeByName(StringHelper.splitCamelCase(EnumGem.values()[meta].getGemName())));
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
        return this.getDefaultState().withProperty(VARIANT,EnumGem.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VARIANT).ordinal());
    }


    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,VARIANT);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> items = Lists.newArrayList();
        ItemStack stack = new ItemStack(this,1,getMetaFromState(state));
        items.add(stack);
        return items;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

}
