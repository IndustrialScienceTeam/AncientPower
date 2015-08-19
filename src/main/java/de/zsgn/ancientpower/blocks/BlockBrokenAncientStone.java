package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBrokenAncientStone extends Block {
	public final static String NAME="brokenancientstone";
	public final static BlockBrokenAncientStone INSTANCE=new BlockBrokenAncientStone();
	
	public BlockBrokenAncientStone() {
		super(Material.rock);
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		this.setHardness(5F);
		this.setResistance(20F);
		this.setHarvestLevel("pickaxe",2);
	}

}