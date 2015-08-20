package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.dimension.AncientPowerWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class BlockAncientBricks extends Block {
    public final static String NAME="ancientbricks";
    public final static BlockAncientBricks INSTANCE=new BlockAncientBricks();

    public BlockAncientBricks() {
        super(Material.rock);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(5F);
        this.setResistance(20F);
        this.setHarvestLevel("pickaxe",2);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos,
            IBlockState state, EntityPlayer playerIn, EnumFacing blockside,
            float hitX, float hitY, float hitZ) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        System.out.println(side);
        if (side == Side.SERVER&&playerIn instanceof EntityPlayerMP){
            WorldServer worldserver = (WorldServer)worldIn;
            EntityPlayerMP playermp = (EntityPlayerMP)playerIn;
            System.out.println(1);
            if (playerIn.ridingEntity == null && playerIn.riddenByEntity == null && playerIn instanceof EntityPlayer)
            {
                playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, AncientPowerWorldProvider.DIMENSIONID);
            }
        }
        return true;
    }

}
