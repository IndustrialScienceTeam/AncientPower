package de.zsgn.ancientpower;


import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockAncientStone;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModCrafting {

	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(BlockAncientBricks.INSTANCE,4), "##", "##", '#', BlockAncientStone.INSTANCE);
		
	}




	
	
	
}
