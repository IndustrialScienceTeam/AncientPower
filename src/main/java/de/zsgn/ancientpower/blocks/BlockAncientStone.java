package de.zsgn.ancientpower.blocks;

import java.util.Random;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.items.ItemChargeableCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockAncientStone extends Block {
	public final static String NAME="ancientstone";
	public final static BlockAncientStone INSTANCE=new BlockAncientStone();
	
	
	
	public BlockAncientStone() {
		super(Material.rock);
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		this.setHardness(5F);
		this.setResistance(20F);
		this.setHarvestLevel("pickaxe",2);
	}
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
	    return Item.getItemFromBlock(BlockBrokenAncientStone.INSTANCE);
	}

	
}