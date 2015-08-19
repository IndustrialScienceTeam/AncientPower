package de.zsgn.ancientpower.proxies;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.CreativeTab;
import de.zsgn.ancientpower.ModCrafting;
import de.zsgn.ancientpower.blocks.BlockAncientBrickStairs;
import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockAncientStone;
import de.zsgn.ancientpower.blocks.BlockBrokenAncientStone;
import de.zsgn.ancientpower.blocks.BlockCrystalEnergyOre;
import de.zsgn.ancientpower.blocks.BlockCrystalframe;
import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import de.zsgn.ancientpower.fluids.FluidLiquidEnergy;
import de.zsgn.ancientpower.items.ItemChargeableCrystal;
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
    

    public void preInit(FMLPreInitializationEvent e) {
        creativeTab=new CreativeTab();

        FluidRegistry.registerFluid(FluidLiquidEnergy.instance);
        GameRegistry.registerBlock(BlockLiquidEnergy.instance,BlockLiquidEnergy.NAME);
        GameRegistry.registerBlock(BlockCrystalEnergyOre.INSTANCE, BlockCrystalEnergyOre.NAME);
        GameRegistry.registerItem(ItemChargeableCrystal.INSTANCE, ItemChargeableCrystal.NAME);
        GameRegistry.registerBlock(BlockCrystalframe.INSTANCE, BlockCrystalframe.NAME);
        GameRegistry.registerBlock(BlockAncientBricks.INSTANCE, BlockAncientBricks.NAME);
        GameRegistry.registerBlock(BlockAncientBrickStairs.INSTANCE, BlockAncientBrickStairs.NAME);
        GameRegistry.registerBlock(BlockAncientStone.INSTANCE, BlockAncientStone.NAME);
        GameRegistry.registerBlock(BlockBrokenAncientStone.INSTANCE, BlockBrokenAncientStone.NAME);
        ModCrafting.initCrafting();
        
    }
     public void init(FMLInitializationEvent e) {
    	
       
         
    }
     public void postInit(FMLPostInitializationEvent e) {
    }
}
