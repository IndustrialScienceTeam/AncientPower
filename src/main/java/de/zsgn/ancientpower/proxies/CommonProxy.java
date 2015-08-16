package de.zsgn.ancientpower.proxies;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.CreativeTab;
import de.zsgn.ancientpower.ItemChargeableCrystal;
import de.zsgn.ancientpower.blocks.BlockAncientStone;
import de.zsgn.ancientpower.blocks.BlockCrystalEnergyOre;
import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import de.zsgn.ancientpower.fluids.FluidLiquidEnergy;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    
    public static CreativeTab creativeTab;
    
    public static Block ancientstone;

    public void preInit(FMLPreInitializationEvent e) {
        creativeTab=new CreativeTab();

        ancientstone=new BlockAncientStone();
        FluidRegistry.registerFluid(FluidLiquidEnergy.instance);
        GameRegistry.registerBlock(BlockLiquidEnergy.instance,BlockLiquidEnergy.NAME);
        GameRegistry.registerBlock(BlockCrystalEnergyOre.INSTANCE, BlockCrystalEnergyOre.NAME);
        GameRegistry.registerBlock(ancientstone, "ancient-stone");
        GameRegistry.registerItem(ItemChargeableCrystal.INSTANCE, ItemChargeableCrystal.NAME);
    }
     public void init(FMLInitializationEvent e) {
         
       
         
    }
     public void postInit(FMLPostInitializationEvent e) {
    }
}
