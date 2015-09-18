package de.zsgn.ancientpower.blocks.env;

import javax.tools.ToolProvider;

import de.zsgn.ancientpower.AncientPower;
import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockCrystalEnergyOre extends Block {
public static final String NAME="crystalenergyore";
public static final BlockCrystalEnergyOre INSTANCE=new BlockCrystalEnergyOre();

/**
 * What's the minimum size of the spawned liquid block(1-8)
 */
public static final int MINQUANTA=1;
/**
 * What's the maxinum size of the spawned liquid block(1-8)
 */
public static final int MAXQUANTA=8;


    public BlockCrystalEnergyOre() {
        super(Material.rock);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
        this.setHardness(5.0F);
        this.setResistance(15F);
        this.setLightLevel(0.5F);
        this.setHarvestLevel("pickaxe", 2);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos,
            IBlockState state, TileEntity te) {
        player.triggerAchievement(StatList.mineBlockStatArray[getIdFromBlock(this)]);
        player.addExhaustion(0.025F);
        if (this.canSilkHarvest(worldIn, pos, worldIn.getBlockState(pos), player) && EnchantmentHelper.getSilkTouchModifier(player))
        {
            java.util.ArrayList<ItemStack> items = new java.util.ArrayList<ItemStack>();
            ItemStack itemstack = this.createStackedBlock(state);

            if (itemstack != null)
            {
                items.add(itemstack);
            }

            net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, worldIn.getBlockState(pos), 0, 1.0f, true, player);
            for (ItemStack stack : items)
            {
                spawnAsEntity(worldIn, pos, stack);
            }
        }
        else
        {
            harvesters.set(player);
            int fortune = EnchantmentHelper.getFortuneModifier(player);
            IBlockState liquidenergyblock=BlockLiquidEnergy.instance.getDefaultState().withProperty(BlockLiquidEnergy.LEVEL, calculateQuanta(fortune));
            worldIn.setBlockState(pos, liquidenergyblock);
            harvesters.set(null);
        }
    }
    /**
     * Calculates the size of the spawned block
     * @param fortune The fortune level
     * @return The size (0-8)
     */
    public int calculateQuanta(int fortune){
        int wonquanta=MINQUANTA;
        wonquanta=wonquanta+RANDOM.nextInt(MAXQUANTA-MINQUANTA-1);
        return wonquanta;
        
    }
    

}
