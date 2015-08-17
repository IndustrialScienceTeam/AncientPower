package de.zsgn.ancientpower.proxies;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.blocks.BlockCrystalEnergyOre;
import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import de.zsgn.ancientpower.items.ItemChargeableCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        final ModelResourceLocation liquid_energymodellocation=new ModelResourceLocation(AncientPower.MODID+":"+BlockLiquidEnergy.NAME);
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockLiquidEnergy.instance), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return liquid_energymodellocation;
            }
        });
        ModelLoader.setCustomStateMapper(BlockLiquidEnergy.instance, new StateMapperBase()
        {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return liquid_energymodellocation;
            }
        });

    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
        .register(ItemChargeableCrystal.INSTANCE, 0, new ModelResourceLocation(AncientPower.MODID  +":"+ItemChargeableCrystal.NAME, "inventory"));
        reg(ancientstone,"bla", 0);
        reg(BlockCrystalEnergyOre.INSTANCE,BlockCrystalEnergyOre.NAME ,0);
    }
    //Nice code by: http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-block/ 
    public static void reg(Block block,String modelname, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
        .register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(AncientPower.MODID  +":"+ modelname, "inventory"));
    }
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
