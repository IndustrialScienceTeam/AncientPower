package de.zsgn.ancientpower.blocks.machines;

import de.zsgn.ancientpower.Util;
import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.tileentities.machines.TileEntityEnergizingFurnace;
import de.zsgn.ancientpower.tileentities.machines.TileEntityEnergyPylon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnergyPylon extends BlockContainer {
public final static BlockEnergyPylon INSTANCE=new BlockEnergyPylon();
public final static String NAME="energpylon";
    protected BlockEnergyPylon() {
        super(Material.iron);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(2.5F);
        this.setResistance(5F);
        this.setHarvestLevel("pickaxe",2);
    }

    /** 
     * Gets invoked, when the block is placed,
     * @param worldIn The world object
     * @param meta The meta id(probaly not updated to blockstates)
     * @return The created TileEntity object.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergyPylon();
    }

}
