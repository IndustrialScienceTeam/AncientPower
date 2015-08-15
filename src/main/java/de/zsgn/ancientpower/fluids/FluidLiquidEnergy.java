package de.zsgn.ancientpower.fluids;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidLiquidEnergy extends Fluid {
public static final String NAME="liquid_energy";
public static final FluidLiquidEnergy instance = new FluidLiquidEnergy();

    public FluidLiquidEnergy() {
        super(NAME, new ResourceLocation(AncientPower.MODID, NAME+"_still"), new ResourceLocation(AncientPower.MODID, NAME+"_flow"));
       this.setLuminosity(30);
       this.setDensity(-1000);
       this.setGaseous(true);
       this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
    }

}
