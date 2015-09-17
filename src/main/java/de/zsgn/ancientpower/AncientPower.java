package de.zsgn.ancientpower;

import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import de.zsgn.ancientpower.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The main class of the mod
 *
 */
@Mod(modid = AncientPower.MODID, version = AncientPower.VERSION)
public class AncientPower
{
    /**
     * Holds the Mod ID for the use in names, registration and so on
     */
    public static final String MODID = "ancientpower";
    
    /**
     * The version of the mod. Only changes after a release.
     */
    public static final String VERSION = "0.1";
    
    /**
     * On the server side, the Server proxy is called to register commands and on the client the client proxy is called to register textures and so on.
     */
    @SidedProxy(clientSide="de.zsgn.ancientpower.proxies.ClientProxy", serverSide="de.zsgn.ancientpower.proxies.ServerProxy")
    public static CommonProxy proxy;
    
    
    /**
     * Called during the PreInit phase of Forge, invokes the preInit function on the corresponding client/server proxy
     * @param e The PreInit Event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }
    /**
     * Called during the init phase of Forge, invokes the init function on the corresponding client/server proxy
     * @param e The Init Event
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {   
		proxy.init(event);
    }
    /**
     * Called during the postinit phase of Forge, invokes the postinit function on the corresponding client/server proxy
     * @param e The PostInit Event
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

    }
}
