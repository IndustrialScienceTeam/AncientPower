package de.zsgn.ancientpower;


import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import de.zsgn.ancientpower.blocks.env.BlockBrokenAncientStone;
import de.zsgn.ancientpower.items.ItemMetalPiece;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModCrafting {

	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(BlockAncientBricks.INSTANCE,4), "##", "##", '#', BlockAncientStone.INSTANCE); //Aus 4 ANcientStone werden 4 Ancient Bricks
		GameRegistry.addSmelting( BlockBrokenAncientStone.INSTANCE, new ItemStack(BlockAncientStone.INSTANCE), 0.5F);//aus Ancient broken stone wird im ofen ancient stone
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_ingot,1), "###","###","###", '#', ItemMetalPiece.INSTANCE); // aus Metal Pices wird Eisen
	}




	
	
	
}
