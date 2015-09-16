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

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEnergizingFurnace();
    }


}
