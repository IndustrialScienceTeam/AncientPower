package de.zsgn.ancientpower.dimension;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockGatewayPillar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class AncientPowerTeleporter extends Teleporter {
    public final static int ROOMSIZE=9;
    public final static int ROOMHEIGHT=6;
    public final int MAXHEIGHT=AncientPowerChunkProvider.MAXY-(2*ROOMHEIGHT);
    public final int MINHEIGHT=5;

    protected final WorldServer worldServerInstance;
    protected final Random random;
    protected final LongHashMap destinationCoordinateCache = new LongHashMap();
    protected final List destinationCoordinateKeys = Lists.newArrayList();
    
    protected double relativeX;
    protected double relativeZ;

    public AncientPowerTeleporter(WorldServer worldIn, double relativeX, double relativeZ) {
        super(worldIn);
        this.worldServerInstance = worldIn;
        this.random = new Random(worldIn.getSeed());
        this.relativeX=relativeX;
        this.relativeZ=relativeZ;
    }

    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
        if (!this.placeInExistingPortal(entityIn, rotationYaw))
        {
            this.makePortal(entityIn);
            this.placeInExistingPortal(entityIn, rotationYaw);
        }
    }

    public boolean placeInExistingPortal(Entity entityIn, float p_180620_2_)
    {
        double d0 = -1.0D;
        int i = MathHelper.floor_double(entityIn.posX);
        int j = MathHelper.floor_double(entityIn.posZ);
        boolean cachePortal = true;
        Object targetpos = BlockPos.ORIGIN;
        long k = ChunkCoordIntPair.chunkXZ2Int(i, j);

        if (this.destinationCoordinateCache.containsItem(k))
        {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(k);
            d0 = 0.0D;
            targetpos = portalposition;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            cachePortal = false;
        }
        else
        {
            BlockPos startpos = new BlockPos(entityIn);

            for (int xoff = -128; xoff <= 128; ++xoff)
            {
                BlockPos newtoscan;

                for (int zoff = -128; zoff <= 128; ++zoff)
                {
                    for (BlockPos toscan = startpos.add(xoff, this.worldServerInstance.getActualHeight() - 1 - startpos.getY(), zoff); toscan.getY() >= 0; toscan = newtoscan)
                    {
                        newtoscan = toscan.down();

                        if (this.worldServerInstance.getBlockState(toscan).getBlock() == BlockGatewayPillar.INSTANCE) 
                        {
                            while (this.worldServerInstance.getBlockState(newtoscan = toscan.down()).getBlock() ==  BlockGatewayPillar.INSTANCE)
                            {
                                toscan = newtoscan;
                            }

                            double distance = toscan.distanceSq(startpos);

                            if (d0 < 0.0D || distance < d0)
                            {
                                d0 = distance;
                                targetpos = toscan;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D)
        {
            if (cachePortal)
            {
                this.destinationCoordinateCache.add(k, new Teleporter.PortalPosition((BlockPos)targetpos, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(k));
            }
            //TODO use strange teleport rotation field frome entity
            double targetx = (double)((BlockPos)targetpos).getX() + relativeX;
            double targety = (double)((BlockPos)targetpos).getY() + 1D;
            double targetz = (double)((BlockPos)targetpos).getZ() + relativeZ;
            entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
            entityIn.setLocationAndAngles(targetx, targety, targetz, entityIn.rotationYaw, entityIn.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean makePortal(Entity p_85188_1_)
    {
        BlockPos pos=findPlaceforPortal(new BlockPos(p_85188_1_));
        if(pos!=null){
            buildRoom(pos, true);
        return true;
        }else{
            return false;
        }
    }

    protected BlockPos findPlaceforPortal(BlockPos start) {
        start=new BlockPos(start.getX(), MathHelper.clamp_int(start.getY(), MAXHEIGHT, MINHEIGHT), start.getZ());
        for (int xoffset = -64; xoffset <= 64; xoffset++) {
            for (int zoffset = -64; zoffset <= 64; zoffset++) {
                for (int yoffset = 0; yoffset <= MAXHEIGHT-start.getY(); yoffset++) {
                  if(buildRoom(start.add(xoffset, yoffset, zoffset),false))
                      return start.add(xoffset, yoffset, zoffset);
                } 
                for (int yoffset = 0; yoffset >= MINHEIGHT-start.getY(); yoffset--) {
                    if(buildRoom(start.add(xoffset, yoffset, zoffset),false))
                        return start.add(xoffset, yoffset, zoffset);
                }
            }   

        }
        return null;

    }

    protected boolean buildRoom(BlockPos pos, boolean build) {
        BlockPos firstcorner=pos.add((ROOMSIZE-1)/2, ROOMHEIGHT-1, (ROOMSIZE-1)/2);
        BlockPos secondcorner=pos.add(-(ROOMSIZE-1)/2, 0, -(ROOMSIZE-1)/2);
        Iterable iterable=BlockPos.getAllInBox(firstcorner, secondcorner);
        for (Object object : iterable) {
            BlockPos tocheck=(BlockPos) object;
            if(!isReplaceableBlock(worldServerInstance.getBlockState(tocheck)))
                return false;
        }
        if(!build)
        return true;
        for (Object object : iterable) {
            BlockPos toreplace=(BlockPos) object;
            worldServerInstance.setBlockState(toreplace, BlockAncientBricks.INSTANCE.getDefaultState());
        }
        iterable=BlockPos.getAllInBox(firstcorner.add(-1,-1,-1), secondcorner.add(1,1,1));
        for (Object object : iterable) {
            BlockPos toclear=(BlockPos) object;
            worldServerInstance.setBlockToAir(toclear);
        }
        for(int i=1;i<=3;i++){
        worldServerInstance.setBlockState(pos.add(0, i, 0), BlockGatewayPillar.INSTANCE.getDefaultState());
        }
        worldServerInstance.setBlockState(pos.add(1, 0, 1), Blocks.glowstone.getDefaultState());
        worldServerInstance.setBlockState(pos.add(-1, 0, -1), Blocks.glowstone.getDefaultState());
        return true;
        
    }

    protected boolean isReplaceableBlock(IBlockState blockState) {
        Block[] toreplace = null;
        if(this.worldServerInstance.provider.getDimensionId()==AncientPowerWorldProvider.DIMENSIONID){
            toreplace=AncientPowerMapGenCaves.replaceableblocks;
        }else{
            toreplace=new Block[]{Blocks.stone,Blocks.dirt, Blocks.gravel};
        }
        for (Block block : toreplace) {
            if(blockState.getBlock().equals(block)){
                return true;
            }
        }
        return false;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    public void removeStalePortalLocations(long p_85189_1_)
    {
        if (p_85189_1_ % 100L == 0L)
        {
            Iterator iterator = this.destinationCoordinateKeys.iterator();
            long j = p_85189_1_ - 600L;

            while (iterator.hasNext())
            {
                Long olong = (Long)iterator.next();
                Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());

                if (portalposition == null || portalposition.lastUpdateTime < j)
                {
                    iterator.remove();
                    this.destinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }

}
