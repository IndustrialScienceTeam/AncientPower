package de.zsgn.mcmod.blocks;

import de.zsgn.mcmod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAncientStone extends Block {

    public BlockAncientStone() {
        super(Material.rock);
        setCreativeTab(ExampleMod.creativeTab);
        setUnlocalizedName("ancient-stone");
    }

}
