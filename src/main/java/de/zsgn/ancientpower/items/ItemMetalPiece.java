package de.zsgn.ancientpower.items;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.ItemFluidContainer;

public class ItemMetalPiece extends Item {

public final static ItemMetalPiece INSTANCE = new ItemMetalPiece();
public final static String NAME="metalpiece";


protected ItemMetalPiece() {
    this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
    this.setCreativeTab(AncientPower.proxy.creativeTab);
    this.setMaxStackSize(64);
}
}
