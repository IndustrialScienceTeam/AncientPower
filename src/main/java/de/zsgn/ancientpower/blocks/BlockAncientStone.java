package de.zsgn.ancientpower.blocks;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAncientStone extends Block {

    public BlockAncientStone() {
        super(Material.rock);
        setCreativeTab(AncientPower.creativeTab);
        setUnlocalizedName(AncientPower.MODID+"."+"ancient-stone");
    }

}
