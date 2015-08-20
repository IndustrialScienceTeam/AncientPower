package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.item.ItemStack;

public  class BlockAncientBrickSlabs extends BlockSlab {
	
	public final static String NAME="ancientbrickslabs";
	public final static BlockAncientBrickSlabs INSTANCE=new BlockAncientBrickSlabs();
	



	
	public BlockAncientBrickSlabs() {
		super(Material.rock);
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		this.setHardness(5F);
		this.setResistance(20F);
		this.setHarvestLevel("pickaxe",2);
	}





	@Override
	public String getUnlocalizedName(int meta) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public boolean isDouble() {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public IProperty getVariantProperty() {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public Object getVariant(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

}
