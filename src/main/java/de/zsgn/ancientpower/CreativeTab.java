package de.zsgn.ancientpower;

import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The creative tab of the mod
 *
 */
public class CreativeTab extends CreativeTabs {

    /**
     * The basic constructor, calls the super constructor, which wants a label(The ModID).
     * The super constructor attaches "itemgroup." for the translation.
     */
    public CreativeTab() {
        super(AncientPower.MODID);
    }
    
    /**
     * This method returns an item, which it renders as an icon for the tab.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
       return BlockAncientBricks.INSTANCE.getItem(null, null);
    }

}
