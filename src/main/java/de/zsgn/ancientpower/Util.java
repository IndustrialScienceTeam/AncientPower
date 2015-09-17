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

/**
 * Holds different utility functions.
 *
 */
public class Util {
    public final static double VELOCITY=0.25D;    


    /**
     * The hand insertion/extraction method for our machine blocks.
     * The player must not sneak. If the hand of the player is empty, it will try to extract something from the machine, on the other side
     * if he has somethin in his hand, the method tries to insert this into the machine. 
     * It uses ISidedInventory methodes and also a custom insert function from the machines.
     * 
     * @see net.minecraft.inventory.ISidedInventory
     * 
     * @param worldIn The world object
     * @param pos The position of the block
     * @param state The blockstate
     * @param playerIn The player, who activated the block
     * @param side The Side on which the player activated
     * @return Whether a action was done or not
     */
    public static boolean rightClickMachineBlock(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side) {
        if(playerIn.isSneaking())
            return false;
        if(worldIn.getTileEntity(pos)instanceof TileEntityWithInv){
            TileEntityWithInv inv=(TileEntityWithInv)worldIn.getTileEntity(pos);
            if(playerIn.inventory.getCurrentItem()==null){
                ItemStack stack=null;
                for (int index : inv.getSlotsForFace(side)) {
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

    
    /**
     * Tells, whether two stacks are combineable or not. Copied from the Vanilla Hopper.
     * @param stack1 The first ItemStack
     * @param stack 2The second ItemStack
     * @return combinable?
     */
    public static boolean canCombine(ItemStack stack1, ItemStack stack2)
    {
        return stack1.getItem() != stack2.getItem() ? false : (stack1.getMetadata() != stack2.getMetadata() ? false : (stack1.stackSize > stack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(stack1, stack2)));
    }

}
