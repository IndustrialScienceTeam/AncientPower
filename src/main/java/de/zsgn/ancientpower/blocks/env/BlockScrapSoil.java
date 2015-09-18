package de.zsgn.ancientpower.blocks.env;

import java.util.Random;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.items.ItemChargeableCrystal;
import de.zsgn.ancientpower.items.ItemMetalPiece;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockScrapSoil extends Block {
	public final static String NAME="scrapsoil";
	public final static BlockScrapSoil INSTANCE=new BlockScrapSoil();
	
	public BlockScrapSoil() {
		super(Material.ground);
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		this.setHardness(0.5F);
		this.setResistance(1F);
		this.setHarvestLevel("pickaxe",0);
	}
	   /** 
     * Returns the Item dropped by the block.
     * @param blockstate The Blockstate
     * @param random A random number generator
     * @param fortune The level of a fortune enhancement
     */
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
	    if (random.nextInt(4) >= 1)
	    	return Item.getItemFromBlock(BlockScrapSoil.INSTANCE);
	    else
		return ItemMetalPiece.INSTANCE;
		
	}
}