package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;

public class BlockAncientBrickSlabs extends Block {
	
	public final static String NAME="ancientbrickslabs";
	public final static BlockAncientBrickSlabs INSTANCE=new BlockAncientBrickSlabs();
	


public static final PropertyInteger ORT = PropertyInteger.create("ort", 0, 1);



//@Override protected BlockState createBlockState() { return new BlockState(this, new IProperty[] {ORT}); } 


	
	public BlockAncientBrickSlabs() {
		super(Material.rock);
		this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
		this.setCreativeTab(AncientPower.proxy.creativeTab);
		this.setHardness(5F);
		this.setResistance(20F);
		this.setHarvestLevel("pickaxe",2);
	}

}
