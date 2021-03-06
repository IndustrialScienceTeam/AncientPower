package de.zsgn.ancientpower.tileentities.machines;

import de.zsgn.ancientpower.blocks.machines.BlockEnergizingFurnace;
import de.zsgn.ancientpower.tileentities.EnergyStrategy;
import de.zsgn.ancientpower.tileentities.IEnergySink;
import de.zsgn.ancientpower.tileentities.TileEntityWithInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityEnergizingFurnace extends TileEntityWithInv implements IEnergySink, IUpdatePlayerListBox {
    public final static int INPUTSLOT=0;
    public final static int OUTPUTSLOT=1;
    public final static int CAPACITY=1000;
    public final static String BURNTICKSKEY="BURNTICKS";
    
    protected int burnticks=-1;
    protected  EnergyStrategy energySinkStrategy;
    
    public TileEntityEnergizingFurnace() {
        super(2, BlockEnergizingFurnace.NAME);
        energySinkStrategy=new EnergyStrategy(CAPACITY, 20*10);
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[]{INPUTSLOT,OUTPUTSLOT};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack,
            EnumFacing direction) {
       return isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack,
            EnumFacing direction) {
        return index==OUTPUTSLOT;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index==INPUTSLOT&&FurnaceRecipes.instance().getSmeltingResult(stack)!=null;
    }

    @Override
    public int getField(int id) {
        switch (id) {
        case 0:
            return burnticks;
        case 1:
            return getMinBurnTicks();
        case 2:
            return energySinkStrategy.getStored();
        case 3:
            return energySinkStrategy.getCapacity();
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
        case 0:
            burnticks=value;
            return;
        case 1:
            return;
        case 2:
            energySinkStrategy.setStored(value);
            return;
        case 3:
            energySinkStrategy.setCapacity(value);
            return;
        }
        return;
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void update() {
        if(burnticks!=-1){
            if(burnticks>=getMinBurnTicks()){
                smeltItem();
                burnticks=-1;
            }else{
                if(energySinkStrategy.consumePower(1,true)){
                burnticks++; 
                }    
            }
        }else if(canSmelt()){
            burnticks=1;
        }
        
    }

    protected int getMinBurnTicks() {
        return 20*5;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
            EntityPlayer playerIn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getGuiID() {
        // TODO Auto-generated method stub
        return null;
    }
    //From the Vanilla Furnace:
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    protected boolean canSmelt()
    {
        if (this.slots[INPUTSLOT] == null||energySinkStrategy.getStored()==0)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.slots[INPUTSLOT]);
            if (itemstack == null) return false;
            if (this.slots[OUTPUTSLOT] == null) return true;
            if (!this.slots[OUTPUTSLOT].isItemEqual(itemstack)) return false;
            int result = slots[OUTPUTSLOT].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[OUTPUTSLOT].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);

            if (this.slots[OUTPUTSLOT] == null)
            {
                this.slots[OUTPUTSLOT] = itemstack.copy();
            }
            else if (this.slots[OUTPUTSLOT].getItem() == itemstack.getItem())
            {
                this.slots[OUTPUTSLOT].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.slots[INPUTSLOT].stackSize;

            if (this.slots[INPUTSLOT].stackSize <= 0)
            {
                this.slots[INPUTSLOT] = null;
            }
        }
    }

    @Override
    public int sinkEnergy(int amount) {
        return energySinkStrategy.sinkEnergy(amount);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energySinkStrategy=new EnergyStrategy(compound);
        burnticks=compound.getInteger(BURNTICKSKEY);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        energySinkStrategy.writeToNBT(compound);
        compound.setInteger(BURNTICKSKEY, burnticks);
    }

    @Override
    public int getCapacity() {
        return energySinkStrategy.getCapacity();
    }

    @Override
    public int getStored() {
       return energySinkStrategy.getStored();
    }



}
