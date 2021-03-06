package de.zsgn.ancientpower.proxies;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.CreativeTab;
import de.zsgn.ancientpower.ModCrafting;
import de.zsgn.ancientpower.blocks.BlockAncientBrickSlabs;
import de.zsgn.ancientpower.blocks.BlockAncientBrickStairs;
import de.zsgn.ancientpower.blocks.BlockAncientBricks;
import de.zsgn.ancientpower.blocks.BlockCrystalframe;
import de.zsgn.ancientpower.blocks.BlockGatewayPillar;
import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import de.zsgn.ancientpower.blocks.env.BlockAncientStone;
import de.zsgn.ancientpower.blocks.env.BlockBrokenAncientStone;
import de.zsgn.ancientpower.blocks.env.BlockCrystalEnergyOre;
import de.zsgn.ancientpower.blocks.env.BlockScrapSoil;
import de.zsgn.ancientpower.blocks.machines.BlockEnergizingFurnace;
import de.zsgn.ancientpower.dimension.AncientPowerWorldProvider;
import de.zsgn.ancientpower.dimension.worldgen.ScrapSoilgen;
import de.zsgn.ancientpower.dimension.worldgen.SimpleWorldGen;
import de.zsgn.ancientpower.fluids.FluidLiquidEnergy;
import de.zsgn.ancientpower.items.ItemChargeableCrystal;
import de.zsgn.ancientpower.items.ItemMetalPiece;
import de.zsgn.ancientpower.tileentities.machines.TileEntityEnergizingFurnace;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
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
        GameRegistry.registerItem(ItemMetalPiece.INSTANCE, ItemMetalPiece.NAME);
        GameRegistry.registerBlock(BlockCrystalframe.INSTANCE, BlockCrystalframe.NAME);
        GameRegistry.registerBlock(BlockScrapSoil.INSTANCE, BlockScrapSoil.NAME);
        GameRegistry.registerBlock(BlockAncientBricks.INSTANCE, BlockAncientBricks.NAME);
        GameRegistry.registerBlock(BlockAncientBrickStairs.INSTANCE, BlockAncientBrickStairs.NAME);
        GameRegistry.registerBlock(BlockAncientBrickSlabs.INSTANCE, BlockAncientBrickSlabs.NAME);
        GameRegistry.registerBlock(BlockAncientStone.INSTANCE, BlockAncientStone.NAME);
        GameRegistry.registerBlock(BlockBrokenAncientStone.INSTANCE, BlockBrokenAncientStone.NAME);
        GameRegistry.registerBlock(BlockGatewayPillar.INSTANCE, BlockGatewayPillar.NAME);
        GameRegistry.registerBlock(BlockEnergizingFurnace.INSTANCE, BlockEnergizingFurnace.NAME);
        
        GameRegistry.registerTileEntity(TileEntityEnergizingFurnace.class, BlockEnergizingFurnace.NAME);
        
        GameRegistry.registerWorldGenerator(new SimpleWorldGen(), 1); //1 is the weight for generator, heavier generators run later
        
        ModCrafting.initCrafting();
        
    }
     public void init(FMLInitializationEvent e){
         DimensionManager.registerProviderType(AncientPowerWorldProvider.DIMENSIONID, AncientPowerWorldProvider.class, false);
    	DimensionManager.registerDimension(AncientPowerWorldProvider.DIMENSIONID, AncientPowerWorldProvider.DIMENSIONID);
    	GameRegistry.registerWorldGenerator(new ScrapSoilgen(), 0);
         
    }
     public void postInit(FMLPostInitializationEvent e) {
    }
}
