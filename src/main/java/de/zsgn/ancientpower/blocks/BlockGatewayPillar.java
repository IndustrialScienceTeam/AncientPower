package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.dimension.AncientPowerTeleporter;
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
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class BlockGatewayPillar extends Block {
    public final static String NAME="gatewaypillar";
    public final static BlockGatewayPillar INSTANCE=new BlockGatewayPillar();

    public BlockGatewayPillar() {
        super(Material.rock);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(2.5F);
        this.setResistance(10F);
        this.setHarvestLevel("pickaxe",1);
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos,
            IBlockState state, EntityPlayer playerIn, EnumFacing blockside,
            float hitX, float hitY, float hitZ) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        System.out.println(side);
        if (side == Side.SERVER&&playerIn instanceof EntityPlayerMP){
            EntityPlayerMP playermp = (EntityPlayerMP)playerIn;
            System.out.println(1);
            if (playerIn.ridingEntity == null && playerIn.riddenByEntity == null && playerIn instanceof EntityPlayer)
            {
                int dim=playermp.dimension==AncientPowerWorldProvider.DIMENSIONID?0:AncientPowerWorldProvider.DIMENSIONID;
                playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, dim, new AncientPowerTeleporter( playermp.mcServer.worldServerForDimension(dim)));
            }
        }
        return true;
    }
}
