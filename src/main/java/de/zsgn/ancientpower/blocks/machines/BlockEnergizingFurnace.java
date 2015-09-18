package de.zsgn.ancientpower.blocks.machines;

import de.zsgn.ancientpower.Util;
import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.tileentities.machines.TileEntityEnergizingFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnergizingFurnace extends BlockContainer {
public final static BlockEnergizingFurnace INSTANCE=new BlockEnergizingFurnace();
public final static String NAME="energizingfurnace";
    protected BlockEnergizingFurnace() {
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
        return new TileEntityEnergizingFurnace();
    }
    /** 
     * Uses the rightClickMachineBlock() from the Util class for the extraction/insertion mechanics.
     * Gets invoked, when the player right clicks the block
     * @see de.zsgn.ancientpower.Util.rightClickMachineBlock(World, BlockPos, IBlockState, EntityPlayer, EnumFacing)
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos,
            IBlockState state, EntityPlayer playerIn, EnumFacing side,
            float hitX, float hitY, float hitZ) {
       return Util.rightClickMachineBlock(worldIn,pos,state,playerIn,side);
    }

}
