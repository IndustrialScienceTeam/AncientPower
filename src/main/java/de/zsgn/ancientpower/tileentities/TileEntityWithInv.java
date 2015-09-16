package de.zsgn.ancientpower.tileentities;

import de.zsgn.ancientpower.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;

public abstract class TileEntityWithInv extends TileEntityLockable implements ISidedInventory, IUpdatePlayerListBox {

    protected ItemStack[] slots;
    protected String defaultname;

    protected TileEntityWithInv(int slotamount, String defaultname) {
        super();
        this.slots = new ItemStack[slotamount];
        this.defaultname = defaultname;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return slots[index];
    }
    //From Vanilla Hopper
    public ItemStack insertStack(ItemStack stack, int index){
        if (slots[index] == null)
        {
            //Forge: BUGFIX: Again, make things respect max stack sizes.
            int max = Math.min(stack.getMaxStackSize(), this.getInventoryStackLimit());
            if (max >= stack.stackSize)
            {
                this.setInventorySlotContents(index , stack);
                return null;
            }
            else
            {
                this.setInventorySlotContents(index, stack.splitStack(max));
                return stack;
            }
        }
        else if (Util.canCombine(slots[index], stack))
        {
            //Forge: BUGFIX: Again, make things respect max stack sizes.
            int max = Math.min(stack.getMaxStackSize(), this.getInventoryStackLimit());
            if (max > slots[index].stackSize)
            {
                int size = Math.min(stack.stackSize, max - slots[index].stackSize);
                stack.stackSize -= size;
                slots[index].stackSize += size;
                if(stack.stackSize<=0){
                    stack=null;
                }
                return stack;
            }
        }
        return stack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.slots[index] != null)
        {
            ItemStack itemstack;

            if (this.slots[index].stackSize <= count)
            {
                itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[index].splitStack(count);

                if (this.slots[index].stackSize == 0)
                {
                    this.slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.slots[index] != null)
        {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public void clear() {
        for (int i = 0; i < this.slots.length; ++i)
        {
            this.slots[i] = null;
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.slots.length)
            {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);
    }
    @Override
    public String getName() {
        return defaultname;
    }


}
