package de.zsgn.ancientpower.dimension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.google.common.collect.Maps;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.Event.*;
import net.minecraftforge.event.terraingen.*;

public class AncientPowerChunkProvider implements IChunkProvider{
    public final static int MAXY=128;

    protected World worldObj;
    protected Random random;
    protected WorldGenLakes waterLakeGenerator;
    protected AncientPowerMapGenCaves mapGenCaves;
    protected List spawnables;

//    protected final ArrayList<MapGenStructure> structureGenerators=new ArrayList<MapGenStructure>();

    public AncientPowerChunkProvider(World worldIn, long seed) {
        this.worldObj=worldIn;
        this.random=new Random(seed);
        waterLakeGenerator=new WorldGenLakes(Blocks.water);
       mapGenCaves=new AncientPowerMapGenCaves();
    }

    public Chunk provideChunk(int x, int z)
    {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        int i1;
        IBlockState blockState;
        for (int k = 0; k <= MAXY; ++k)
        {
            if(k==0||k==MAXY){
                blockState=Blocks.bedrock.getDefaultState();
            }else{
                blockState=BlockAncientStone.INSTANCE.getDefaultState(); 
            }
            for (int l = 0; l < 16; ++l)
            {
                for (i1 = 0; i1 < 16; ++i1)
                {
                    chunkprimer.setBlockState(l, k, i1, blockState);
                }
            }
        }

//        Iterator<MapGenBase> iterator = this.structureGenerators.iterator();
//        while (iterator.hasNext())
//        {
//            MapGenBase mapgenbase = iterator.next();
//            mapgenbase.func_175792_a(this, this.worldObj, x, z, chunkprimer);
//
//        }
        this.mapGenCaves.func_175792_a(this, this.worldObj, x, z, chunkprimer); 

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();
        for (i1 = 0; i1 < abyte.length; ++i1)
        {
            abyte[i1] = (byte)abiomegenbase[i1].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        this.random.setSeed(this.worldObj.getSeed());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.worldObj.getSeed());
//        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
//        Iterator<MapGenBase> iterator = this.structureGenerators.iterator();
        boolean strucutureuseschunk=false;
//        while (iterator.hasNext())
//        {
//            MapGenBase base=iterator.next();
//                strucutureuseschunk = mapgenstructure.func_175794_a(this.worldObj, this.random, chunkcoordintpair);
//            }
//            }

        if (strucutureuseschunk && this.random.nextInt(4) == 0)
        {
            this.waterLakeGenerator.generate(this.worldObj, this.random, blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
        }


        //        if (this.hasDungeons)
        //        {
        //            for (int k1 = 0; k1 < 8; ++k1)
        //            {
        //                (new WorldGenDungeons()).generate(this.worldObj, this.random, blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
        //            }
        //        }
    }

    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        return false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return AncientPowerWorldProvider.NAME.toLowerCase(Locale.US).replace(' ', '_');
    }

    public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
    {
        return spawnables;
    }

    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_)
    {
        return null; // Just no
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {
//        Iterator iterator = this.structureGenerators.iterator();
//
//        while (iterator.hasNext())
//        {
//            MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();
//            mapgenstructure.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
//        }
    }

    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }
}