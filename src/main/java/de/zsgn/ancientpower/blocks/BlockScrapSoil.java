package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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

}