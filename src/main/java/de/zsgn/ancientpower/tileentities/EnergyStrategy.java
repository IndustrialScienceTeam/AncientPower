package de.zsgn.ancientpower.tileentities;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyStrategy implements IEnergySink {
    private final static String CAPACITY="capacity";
    private final static String STORED="stored";
    private final static String NAME="energy";
    
    protected int capacity=100;
    protected int stored=100;
    
    public EnergyStrategy(int capacity, int stored) {
        this.capacity = capacity;
        this.stored = stored;
    }
    public EnergyStrategy(NBTTagCompound compound) {
        super();
        readFromNBT(compound);
    }

    
    
    
    @Override
    public int sinkEnergy(int amount) {
       int tostore=Math.min(amount, capacity-stored);
       stored=amount+stored;
       return amount-tostore;
    }
    
    public boolean consumePower(int amount, boolean doconsume) {
        if(stored-amount>=0){
            if(doconsume)
            stored=stored-amount;
            return true;
        }
        return false;
    }
    
    
    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagCompound toread=compound.getCompoundTag(NAME);
        capacity=toread.getInteger(CAPACITY);
        stored=toread.getInteger(STORED);
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        NBTTagCompound tosave=new NBTTagCompound();
        tosave.setInteger(CAPACITY, capacity);
        tosave.setInteger(STORED, stored);
        compound.setTag(NAME, tosave);
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getStored() {
        return stored;
    }
    public void setStored(int stored) {
        this.stored = stored;
    }

}
