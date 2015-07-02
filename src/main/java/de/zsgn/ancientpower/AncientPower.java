package de.zsgn.ancientpower;

import de.zsgn.ancientpower.blocks.BlockAncientStone;
import de.zsgn.ancientpower.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = AncientPower.MODID, version = AncientPower.VERSION)
public class AncientPower
{
    public static final String MODID = "ancientpower";
    public static final String VERSION = "0.1";
    
    @SidedProxy(clientSide="de.zsgn.ancientpower.proxies.ClientProxy", serverSide="de.zsgn.ancientpower.proxies.ServerProxy")
    public static CommonProxy proxy;
    
    public static CreativeTab creativeTab;
    
    public static Block ancientstone;
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {   
        creativeTab=new CreativeTab();
        
        ancientstone=new BlockAncientStone();
        
		registerBlocks();
		
		proxy.init(event);
    }
    protected void registerBlocks() {
        GameRegistry.registerBlock(ancientstone, "ancient-stone");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

    }
}
