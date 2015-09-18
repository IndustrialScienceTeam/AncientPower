package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.fluids.FluidLiquidEnergy;
import de.zsgn.ancientpower.proxies.CommonProxy;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * The basic liquid energy block.
 *
 */
public class BlockLiquidEnergy extends BlockFluidFinite {
    public static final BlockLiquidEnergy instance=new BlockLiquidEnergy();
    public static final String NAME="Block"+FluidLiquidEnergy.NAME;

    public BlockLiquidEnergy() {
        super(FluidLiquidEnergy.instance, Material.water);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        
    }

    /** 
     * The drain method, returns a block as a fluid stack
     * @param worldIn The world object
     * @param pos The position of the block
     * @param doDrain Remove the block, or only simulate it
     * @return The FluidStack
     */
    public FluidStack drain(World worldIn, BlockPos pos,
            boolean doDrain) {
        int resultamount=MathHelper.floor_float(getQuantaPercentage(worldIn, pos) * FluidContainerRegistry.BUCKET_VOLUME);
        if (doDrain)
        {
            worldIn.setBlockToAir(pos);
        }

        return new FluidStack(getFluid(),resultamount  );
    }
    

}
