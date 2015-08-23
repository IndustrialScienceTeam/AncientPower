package de.zsgn.ancientpower.blocks;

import java.util.List;

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
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
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
        BlockPos bottompillar=getBottomofPillar(worldIn, pos);
        if(bottompillar==null){
            if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
                playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("msg."+AncientPower.MODID+"."+NAME+".incomplete")));
            return false;
        }else{
            teleport(worldIn, bottompillar);
            return true;
        }
    }
    public void teleport(World worldIn, BlockPos pos){
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            BlockPos firstcorner=pos.add((AncientPowerTeleporter.ROOMSIZE-1)/2, AncientPowerTeleporter.ROOMHEIGHT, (AncientPowerTeleporter.ROOMSIZE-1)/2);
            BlockPos secondcorner=pos.add(-(AncientPowerTeleporter.ROOMSIZE-1)/2, 0, -(AncientPowerTeleporter.ROOMSIZE-1)/2);
            List entitylist=worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(secondcorner, firstcorner));
            int dim;
            for (Object object : entitylist) {
                if(object instanceof EntityPlayerMP){
                    EntityPlayerMP toteleport=(EntityPlayerMP) object;
                    dim=toteleport.dimension==AncientPowerWorldProvider.DIMENSIONID?0:AncientPowerWorldProvider.DIMENSIONID;
                    toteleport.mcServer.getConfigurationManager().transferPlayerToDimension(toteleport, dim, new AncientPowerTeleporter( toteleport.mcServer.worldServerForDimension(dim), toteleport.getPositionVector().xCoord-pos.getX(),toteleport.getPositionVector().zCoord-pos.getZ()));

                }
            }
        }
    }
    public BlockPos getBottomofPillar(World worldIn, BlockPos pos) {
        BlockPos lastpillarblock=pos;
        IBlockState bottomblockstate;
        for (int i = 1; i <=3; i++) {
            bottomblockstate=worldIn.getBlockState(pos.down(i));
            if(bottomblockstate.getBlock()!=INSTANCE){
                lastpillarblock=pos.down(i-1);
                break;
            }
        }
        for (int i = 1; i <=2; i++) {
            if(worldIn.getBlockState(lastpillarblock.up(i)).getBlock()!=INSTANCE){
                return null;
            } 
        }
        return lastpillarblock;
    }
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn,
            BlockPos pos) {
        int i;
        IBlockState bottomblockstate;
        for (i = -1; i <2; i++) {
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
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
