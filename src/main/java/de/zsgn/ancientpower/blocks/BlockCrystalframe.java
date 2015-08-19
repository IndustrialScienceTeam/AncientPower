package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCrystalframe extends Block {
    public static final String NAME="crystalframe";
    public static final BlockCrystalframe INSTANCE=new BlockCrystalframe();
    public BlockCrystalframe() {
        super(Material.iron);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(5.0F);
        this.setResistance(15F);
        this.setLightLevel(0.5F);
        this.setHarvestLevel("pickaxe", 2);

    }
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
