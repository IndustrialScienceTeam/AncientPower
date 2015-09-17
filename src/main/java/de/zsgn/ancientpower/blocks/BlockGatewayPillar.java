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
    public final static BlockGatewayPillar INSTANCE=new BlockGatewayPillar();
    
    /**
     * The property used for the blockstate, needs to be defined here.
     * It can save an int from 0 to 2.
     */
    public final static PropertyInteger RELPOS=PropertyInteger.create("relpos", 0, 2);

    public BlockGatewayPillar() {
        super(Material.rock);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(2.5F);
        this.setResistance(10F);
        this.setHarvestLevel("pickaxe",1);
    }
    
    /** 
     * Sends a message to the player, when the pillar is incomplete, otherwise it teleports the player to the dimension.
     * Gets invoked, when the player right clicks the block
     */
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
    
    /**
     * Teleports all Entities in the range of the pillar into the other dimension using a AncientPowerTeleporter object.
     * @see de.zsgn.ancientpower.dimension.AncientPowerTeleporter
     * @param worldIn The world object.
     * @param pos The position of the block.
     */
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
    /**
     * Finds the bottom block of the pillar
     * @param worldIn The world object.
     * @param pos The starting position
     * @return The position of the bottom pillar, null, when the pillar is incomplete.
     */
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
    /**
     * Returns a differnt blockstate(Used for finding the right model from the blockstates file) depending on its position in the pillar
     * @param state The state saved for the block
     * @param worldIn The world access
     * @param pos The position of the block
     * @return The blockstate
     */
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
    /**
     * Returns metadata to save, generated from the blockstate. 
     * This block generates its state dynamically, so there is no need to save the blockstate.
     * @param state The blockstate of the block
     * @return The meta ID
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    /** Creates the base for the blockstate of this block with its properties, in this case only the position in the pillar.
     * @return The empty blockstate
     */
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{RELPOS});
    }
    
    /**  Is the block 1m^3? Important, because if it is minecraft is not rendering surfaces next to it.
     * @return Is the block 1m^3?
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
