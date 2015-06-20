package de.zsgn.mcmod.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy {

    abstract public void preInit(FMLPreInitializationEvent e);
    abstract public void init(FMLInitializationEvent e);
    abstract public void postInit(FMLPostInitializationEvent e);
}
