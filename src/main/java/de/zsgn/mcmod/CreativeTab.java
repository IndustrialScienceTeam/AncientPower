package de.zsgn.mcmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

    public CreativeTab() {
        super(ExampleMod.MODID);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
       return ExampleMod.ancientstone.getItem(null, null);
    }

}
