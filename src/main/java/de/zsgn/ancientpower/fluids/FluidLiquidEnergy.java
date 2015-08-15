package de.zsgn.ancientpower.fluids;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquidEnergy extends Fluid {
public static final String NAME="liquid_energy";
public static final FluidLiquidEnergy instance = new FluidLiquidEnergy();

    public FluidLiquidEnergy() {
        super(NAME, new ResourceLocation(AncientPower.MODID, NAME+"_still"), new ResourceLocation(AncientPower.MODID, NAME+"_flow"));
       this.setLuminosity(20);
       this.setDensity(500);
       this.setViscosity(500);
       this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
    }


}
