package de.zsgn.ancientpower.blocks;

import com.sun.org.apache.regexp.internal.RE;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.dimension.AncientPowerTeleporter;
import de.zsgn.ancientpower.dimension.AncientPowerWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class BlockGatewayPillar extends Block {
    public final static String NAME="gatewaypillar";
    public final static PropertyInteger RELPOS=PropertyInteger.create("relpos", 0, 2);
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
        if(playerIn.isSneaking()){
            return false;
        }
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER&&playerIn instanceof EntityPlayerMP){
            EntityPlayerMP playermp = (EntityPlayerMP)playerIn;
            if (playerIn.ridingEntity == null && playerIn.riddenByEntity == null && playerIn instanceof EntityPlayer)
            {
                int dim=playermp.dimension==AncientPowerWorldProvider.DIMENSIONID?0:AncientPowerWorldProvider.DIMENSIONID;
                playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, dim, new AncientPowerTeleporter( playermp.mcServer.worldServerForDimension(dim)));
            }
        }
        return true;
    }
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn,
            BlockPos pos) {
        int i;
        IBlockState bottomblockstate;
        for (i = 0; i <2; i++) {
            bottomblockstate=worldIn.getBlockState(pos.down(i+1));
            if(bottomblockstate.getBlock()!=INSTANCE)
                break;
        }
            return state.withProperty(RELPOS, i);
        
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos,
            IBlockState state) {
        // TODO Auto-generated method stub
        return super.getCollisionBoundingBox(worldIn, pos, state);
       
    }
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{RELPOS});
    }
}