package de.zsgn.ancientpower.tileentities;

import net.minecraft.nbt.NBTTagCompound;

public class EnergySinkStrategy implements IEnergySink {
    private final static String CAPACITY="capacity";
    private final static String STORED="stored";
    private final static String NAME="energy";
    
    public int capacity=100;
    public int stored=100;
    
    public EnergySinkStrategy(int capacity, int stored) {
        this.capacity = capacity;
        this.stored = stored;
    }
    public EnergySinkStrategy(NBTTagCompound compound) {
        super();
        readFromNBT(compound);
    }

    
    
    
    @Override
    public int sinkEnergy(int amount) {
       int tostore=Math.min(amount, capacity-stored);
       stored=amount+stored;
       return amount-tostore;
    }
    
    public boolean consumePower(int amount) {
        if(stored-amount>=0){
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

}
