package de.zsgn.ancientpower.dimension;

import java.util.ArrayList;
import java.util.Locale;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
//import net.minecraft.world.gen.AncientPowerChunkProvider;

public class AncientPowerWorldProvider extends WorldProvider {
    public final static String NAME="The Extinct Realm";
    public static int DIMENSIONID=7;
    @Override
    public String getDimensionName() {
        return NAME;
    }

    @Override
    public String getInternalNameSuffix() {
        return NAME.toLowerCase(Locale.US).replace(' ', '_');
    }
    @Override
    public void registerWorldChunkManager() {        
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.plains, 0.0F); 
        this.dimensionId = DIMENSIONID;
    } 
    @Override
    public IChunkProvider createChunkGenerator() {
        return new AncientPowerChunkProvider(worldObj, worldObj.getSeed());
    }
    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }
    @Override
    public boolean canRespawnHere()
    {
        return false;
    }
}
