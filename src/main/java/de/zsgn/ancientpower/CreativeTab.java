package de.zsgn.ancientpower;

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
       return AncientPower.proxy.ancientstone.getItem(null, null);
    }

}
