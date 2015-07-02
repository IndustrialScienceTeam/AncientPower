package de.zsgn.ancientpower.proxies;

import de.zsgn.ancientpower.AncientPower;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FMLInitializationEvent e) {
        reg(AncientPower.ancientstone, 0);
    }
    //Nice code by: http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-block/ 
    public static void reg(Block block, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
        .register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(AncientPower.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        // TODO Auto-generated method stub

    }

}
