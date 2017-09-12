package com.m4thg33k.gemulation.items;

import com.m4thg33k.gemulation.Gemulation;
import com.m4thg33k.gemulation.core.util.ItemNBTHelper;
import com.m4thg33k.gemulation.core.util.StringHelper;
import com.m4thg33k.gemulation.gui.GemulationGuiHandler;
import com.m4thg33k.gemulation.lib.Constants;
import com.m4thg33k.gemulation.lib.Names;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.silentchaos512.gems.api.lib.EnumMaterialGrade;
import net.silentchaos512.gems.init.ModItems;
import net.silentchaos512.gems.lib.EnumGem;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemGemBag extends Item {

    public static final String TAG_ITEMS = "InvItems";
    public static final String TAG_SLOT = "Slot";

    public ItemGemBag()
    {
        super();
        this.setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
        this.setCreativeTab(Gemulation.tabGemulation);
        this.setUnlocalizedName(Names.GEM_BAG);
        this.setRegistryName(Gemulation.MODID,Names.GEM_BAG);

        this.setHasSubtypes(true);
    }

    public static void setStackNBT(ItemStack stack, ItemStack[] inventorySlots)
    {
        NBTTagList list = new NBTTagList();
        for (int i=0;i<inventorySlots.length;i++)
        {
            if (inventorySlots[i] != null)
            {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte(TAG_SLOT,(byte)i);
                inventorySlots[i].writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        ItemNBTHelper.setList(stack,TAG_ITEMS,list);
    }

    public static ItemStack[] getStacksFromNBT(ItemStack stack)
    {
        NBTTagList list = ItemNBTHelper.getList(stack,TAG_ITEMS,10,false);
        ItemStack[] inventorySlots = new ItemStack[32];
        for (int i=0;i<list.tagCount();i++)
        {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            byte slot = stackTag.getByte(TAG_SLOT);
            if (slot>=0 && slot<inventorySlots.length)
            {
                inventorySlots[slot] = ItemStack.func_77949_a(stackTag);
            }
        }

        return  inventorySlots;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile!=null)
        {
            if (!worldIn.isRemote)
            {
                IItemHandler inventory = null;
                if (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null))
                {
                    inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);
                }
                else if (tile instanceof IInventory)
                {
                    inventory = new InvWrapper((IInventory)tile);
                }

                if (inventory==null)
                {
                    return EnumActionResult.SUCCESS;
                }

                ItemStack[] gemStacks = this.getStacksFromNBT(stack);
                ItemStack[] newStacks = new ItemStack[gemStacks.length];
                boolean transferedAny = false;

                int i=0;
                for (ItemStack gems : gemStacks)
                {
                    if (gems != null)
                    {
                        newStacks[i] = ItemHandlerHelper.insertItemStacked(inventory,gems,false);
                        int count = gems.stackSize;
                        if (newStacks[i] != null)
                        {
                            count = gems.stackSize - newStacks[i].stackSize;
                            transferedAny |= count > 0;
                        }
                    }
                    i++;
                }

                setStackNBT(stack,newStacks);
                if (transferedAny && tile instanceof TileEntityChest)
                {
                    playerIn.displayGUIChest((TileEntityChest)tile);
                }
            }

            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (hand != EnumHand.MAIN_HAND || playerIn.isSneaking())
        {
            if (playerIn.isSneaking())
            {
                gatherGems(itemStackIn, playerIn);
            }
            return ActionResult.newResult(EnumActionResult.FAIL,itemStackIn);
        }
        playerIn.openGui(Gemulation.instance, GemulationGuiHandler.GEM_BAG_GUI,worldIn,0,0,0);
//        itemStackIn.setItemDamage((itemStackIn.getItemDamage()+32)%64);
        return ActionResult.newResult(EnumActionResult.SUCCESS,itemStackIn);
    }

    private void gatherGems(ItemStack itemStack,EntityPlayer player)
    {
        if (itemStack==null || !(itemStack.getItem() instanceof ItemGemBag))
        {
            return;
        }

        ItemStack[] bagInventory = ItemGemBag.getStacksFromNBT(itemStack);
        boolean didChange = false;

        for (int i=0;i<player.inventory.getSizeInventory();i++)
        {
            if (i == player.inventory.currentItem)
            {
                continue;
            }

            ItemStack inventory = player.inventory.getStackInSlot(i);
            if (inventory!=null && inventory.getItem()== ModItems.gem && inventory.stackSize>0 && inventory.getItemDamage()<32 && EnumMaterialGrade.fromStack(inventory) == EnumMaterialGrade.NONE)
            {
                int variant = inventory.getItemDamage();
                ItemStack stackAt = bagInventory[variant];
                if (stackAt==null)
                {
                    bagInventory[variant] = inventory.copy();
                    player.inventory.setInventorySlotContents(i,null);
                    didChange = true;
                }
                else
                {
                    int stackInBagSize = stackAt.stackSize;
                    int stackSize = inventory.stackSize;
                    int spare = 64 - stackInBagSize;
                    int pass = Math.min(spare,stackSize);
                    if (pass>0)
                    {
                        stackAt.stackSize += pass;
                        inventory.stackSize -= pass;
                        if (inventory.stackSize == 0)
                        {
                            player.inventory.setInventorySlotContents(i,null);
                        }
                        didChange = true;
                    }
                }
            }
        }

        if (didChange)
        {
            ItemGemBag.setStackNBT(itemStack,bagInventory);
        }
    }

    @SubscribeEvent
    public void onPickupItem(EntityItemPickupEvent event)
    {
        if (event.isCanceled())
        {
            return;
        }
        ItemStack stack = event.getItem().getEntityItem();
        if (stack.getItem() == ModItems.gem && stack.stackSize >0 && EnumMaterialGrade.fromStack(stack) == EnumMaterialGrade.NONE)
        {
            int variant = stack.getItemDamage();
            if (variant>=32)
            {
                return;
            }

            for (int i=0;i<event.getEntityPlayer().inventory.getSizeInventory();i++) {
                if (i == event.getEntityPlayer().inventory.currentItem) {
                    continue;
                }

                ItemStack inventory = event.getEntityPlayer().inventory.getStackInSlot(i);
                if (inventory != null && inventory.getItem() instanceof ItemGemBag) {
                    ItemStack[] bagInventory = this.getStacksFromNBT(inventory);
                    ItemStack stackAt = bagInventory[variant];
                    boolean didChange = false;
                    if (stackAt == null) {
                        bagInventory[variant] = stack.copy();
                        stack.stackSize = 0;
                        didChange = true;
                    } else {
                        int stackAtSize = stackAt.stackSize;
                        int stackSize = stack.stackSize;
                        int spare = 64 - stackAtSize;
                        int pass = Math.min(spare, stackSize);
                        if (pass > 0) {
                            stackAt.stackSize += pass;
                            stack.stackSize -= pass;
                            didChange = true;
                        }
                    }

                    if (didChange) {
                        setStackNBT(inventory, bagInventory);
                    }
                }
                if (stack.stackSize == 0) {
                    return;
                }
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage<32?damage:0;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + stack.getItemDamage()%32;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i=0;i<32;i++)
        {
            subItems.add(new ItemStack(itemIn,1,i));
        }
    }

    public static boolean isEmpty(ItemStack stack)
    {
        ItemStack[] stacks = ItemGemBag.getStacksFromNBT(stack);

        for (int i=0;i<stacks.length;i++)
        {
            if (stacks[i]!=null)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced) {

        list.add(TextFormatting.GOLD + (isEmpty(stack)?"<EMPTY>":"Not Empty!"));
    }
}
