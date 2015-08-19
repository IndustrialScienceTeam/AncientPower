package de.zsgn.ancientpower;


import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockAncientStone;
import de.zsgn.ancientpower.blocks.BlockBrokenAncientStone;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModCrafting {

	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(BlockAncientBricks.INSTANCE,4), "##", "##", '#', BlockAncientStone.INSTANCE);
		GameRegistry.addSmelting( BlockBrokenAncientStone.INSTANCE, new ItemStack(BlockAncientStone.INSTANCE), 0.5F);
	}




	
	
	
}