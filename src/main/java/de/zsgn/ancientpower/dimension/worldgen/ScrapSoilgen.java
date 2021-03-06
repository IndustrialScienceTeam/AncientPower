package de.zsgn.ancientpower.dimension.worldgen;

import java.util.Random;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import de.zsgn.ancientpower.blocks.env.BlockScrapSoil;
import de.zsgn.ancientpower.dimension.AncientPowerWorldProvider;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ScrapSoilgen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {   
    	switch (world.provider.getDimensionId()) {
        case 0: //Overworld

            break;
        case -1: //Nether

            break;
        case 1: //End

            break;
            //TODO Get dimension id from a config or sth like that
        case 7:// Own world
        	this.runGenerator(this.ScrapSoilgen, world, random, chunkX, chunkZ, 20, 0, 64);
            break;
        }
    }
    
    
    private WorldGenerator ScrapSoilgen; 

    public ScrapSoilgen() {
        this.ScrapSoilgen = new WorldGenMinable(BlockScrapSoil.INSTANCE.getDefaultState(), 64,BlockHelper.forBlock(BlockAncientStone.INSTANCE));
    }
    
    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
   }
    
}
    

