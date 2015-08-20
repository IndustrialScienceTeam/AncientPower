package de.zsgn.ancientpower.dimension;

import de.zsgn.ancientpower.blocks.BlockAncientStone;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class AncientPowerMapGenCaves extends MapGenCaves {
protected final IBlockState[] replaceableblocks={BlockAncientStone.INSTANCE.getDefaultState()};
    @Override
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX,
            int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
        if (this.func_175793_a(state, up))
        {
            if (y < 10)
            {
                data.setBlockState(x, y, z, Blocks.lava.getDefaultState());
            }
            else
            {
                data.setBlockState(x, y, z, Blocks.air.getDefaultState());

//                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
//                {
//                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
//                }
            }
        }
    }
    @Override
    protected boolean func_175793_a(IBlockState blocktodig, IBlockState blogabove)
    {
        return true;
//        if(blogabove.getBlock().equals(Blocks.water)){
//            return false;
//        }
//        for (int i = 0; i < replaceableblocks.length; i++) {
//            if(replaceableblocks[i].equals(blocktodig.getBlock()))
//                return true;
//        }
//        return false;
        
    }

}
