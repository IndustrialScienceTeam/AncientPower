package de.zsgn.ancientpower;

import de.zsgn.ancientpower.tileentities.TileEntityWithInv;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Util {
    public final static double VELOCITY=0.25D;    


    public static boolean rightClickMachineBlock(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side) {
        if(playerIn.isSneaking())
            return false;
        if(worldIn.getTileEntity(pos)instanceof TileEntityWithInv){
            TileEntityWithInv inv=(TileEntityWithInv)worldIn.getTileEntity(pos);
            if(playerIn.inventory.getCurrentItem()==null){
                ItemStack stack=null;
                for (int index : inv.getSlotsForFace(side)) {
                    System.out.println("LOL");
                    if(inv.canExtractItem(index, inv.getStackInSlot(index), side)){
                        stack=inv.decrStackSize(index, 1);
                        break;
                    }
                }
                if(stack==null)
                    return false;
                    EntityItem entityItem=new EntityItem(worldIn,(double) pos.getX()+0.5+side.getFrontOffsetX(), (double)pos.getY()+0.5+side.getFrontOffsetY(), (double)pos.getZ()+0.5+side.getFrontOffsetZ(), stack);
                    entityItem.setVelocity(side.getFrontOffsetX()*0.25D, side.getFrontOffsetY()*0.25D, side.getFrontOffsetZ()*0.25D);
                    worldIn.spawnEntityInWorld(entityItem);
                return true;
            }else{
                for (int index : inv.getSlotsForFace(side)) {
                    if(inv.canInsertItem(index, playerIn.inventory.getCurrentItem(), side)){
                        playerIn.inventory.mainInventory[playerIn.inventory.currentItem]=inv.insertStack(playerIn.inventory.getCurrentItem(),index);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //From the Hopper
    public static boolean canCombine(ItemStack stack1, ItemStack stack2)
    {
        return stack1.getItem() != stack2.getItem() ? false : (stack1.getMetadata() != stack2.getMetadata() ? false : (stack1.stackSize > stack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(stack1, stack2)));
    }

}
