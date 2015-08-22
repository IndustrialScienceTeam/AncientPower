package de.zsgn.ancientpower.dimension.worldgen;

import java.util.Random;

import de.zsgn.ancientpower.blocks.BlockAncientStone;
import de.zsgn.ancientpower.dimension.AncientPowerChunkProvider;
import de.zsgn.ancientpower.dimension.AncientPowerWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SimpleWorldGen implements IWorldGenerator {
    protected final BlockGen[] blockgens={new BlockGen(1, Blocks.dirt.getDefaultState(), 8)};
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if(world.provider.getDimensionId()==AncientPowerWorldProvider.DIMENSIONID){
            int x=0;
            int y=0;
            int z=0;
            BlockHelper replaces=BlockHelper.forBlock(BlockAncientStone.INSTANCE);
            for (BlockGen blockgen : blockgens) {
                if(random.nextInt(blockgen.chance)==0){
                    x=random.nextInt(16); //16 chunksize
                    y=random.nextInt(AncientPowerChunkProvider.MAXY);
                    z=random.nextInt(16); 
                    y=MathHelper.clamp_int(y, 5, AncientPowerChunkProvider.MAXY);
                    new WorldGenMinable(blockgen.blockstate, blockgen.blockamount, replaces).generate(world, random, new BlockPos(x, y, z));
                }
            }
        }
    }
    public class BlockGen{
        protected int chance;
        protected IBlockState blockstate;
        protected int blockamount;
        protected BlockGen(int chance, IBlockState blockstate, int blockamount) {
            this.chance = chance;
            this.blockstate = blockstate;
            this.blockamount = blockamount;
        }
    }

}
