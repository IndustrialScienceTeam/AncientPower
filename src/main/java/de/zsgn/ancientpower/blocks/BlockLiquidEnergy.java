package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.fluids.FluidLiquidEnergy;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockLiquidEnergy extends BlockFluidClassic {
    public static final BlockLiquidEnergy instance=new BlockLiquidEnergy();
    public static final String NAME="Block"+FluidLiquidEnergy.NAME;

    public BlockLiquidEnergy() {
        super(FluidLiquidEnergy.instance, Material.water);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
    }

}
