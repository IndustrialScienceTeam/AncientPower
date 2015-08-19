package de.zsgn.ancientpower;

import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockAncientStone;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

    public CreativeTab() {
        super(AncientPower.MODID);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
       return BlockAncientBricks.INSTANCE.getItem(null, null);
    }

}
