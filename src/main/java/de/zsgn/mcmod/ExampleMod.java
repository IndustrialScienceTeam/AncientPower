package de.zsgn.mcmod;

import de.zsgn.mcmod.proxies.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "mcmod";
    public static final String VERSION = "0.1";
    
    @SidedProxy(clientSide="de.zsgn.mcmod.proxies.ClientProxy", serverSide="de.zsgn.mcmod.proxies.ServerProxy")
    public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {   
		proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

    }
}
