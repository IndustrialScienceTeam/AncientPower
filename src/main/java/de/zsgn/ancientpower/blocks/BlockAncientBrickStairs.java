package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

public class BlockAncientBrickStairs extends BlockStairs {
	public final static String NAME="ancientbrickstairs";
	public final static BlockAncientBrickStairs INSTANCE=new BlockAncientBrickStairs();
	
	public BlockAncientBrickStairs() {
		super(BlockAncientBricks.INSTANCE.getDefaultState());
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		
	}

}