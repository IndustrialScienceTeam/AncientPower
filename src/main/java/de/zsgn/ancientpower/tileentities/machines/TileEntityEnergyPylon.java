package de.zsgn.ancientpower.tileentities.machines;

import java.util.ArrayList;
import java.util.Iterator;

import de.zsgn.ancientpower.tileentities.EnergyStrategy;
import de.zsgn.ancientpower.tileentities.IEnergyPylon;
import de.zsgn.ancientpower.tileentities.IEnergySink;
import de.zsgn.ancientpower.tileentities.IEnergyTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityEnergyPylon extends TileEntity implements IUpdatePlayerListBox, IEnergyPylon {
    public static final int PYLONTOLERANCE = 50;
    protected EnergyStrategy energystrat;
    protected ArrayList<IEnergyTileEntity> clients;
    protected int tickstowait=20;
    protected int energyburstsize=500;
    protected int tickswaited=0;
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energystrat=new EnergyStrategy(compound);
    }
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        energystrat.writeToNBT(compound);
    }
    @Override
    public void update() {
       if(tickswaited>=tickstowait){
           doEnergyBurst();
       }else {
        tickswaited++;
    }
        
    }
    public void doEnergyBurst() {
       if(clients.size()>0&&energystrat.consumePower(energyburstsize, false)){
           int amountperclient=MathHelper.floor_double((double)energyburstsize/clients.size());
           for (IEnergyTileEntity energytile : clients) {
            if(energytile instanceof IEnergySink){
                IEnergySink sink=(IEnergySink) energytile;
                if(energystrat.consumePower(amountperclient, true)){
                sink.sinkEnergy(amountperclient);
                }
            }else if (energytile instanceof IEnergyPylon) {
                IEnergyPylon pylon=(IEnergyPylon) energytile;
                if(MathHelper.abs(this.getStored()-pylon.getStored())>PYLONTOLERANCE){
                    IEnergyPylon toremoveenergy=this.getStored()>pylon.getStored()?this:pylon;
                    IEnergyPylon toaddenergy=this.getStored()<pylon.getStored()?this:pylon;
                    //TODO Balance Energy
                }
            }
        }
       }
        
    }
    @Override
    public int sinkEnergy(int amount) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getCapacity() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getStored() {
        // TODO Auto-generated method stub
        return 0;
    }
}
