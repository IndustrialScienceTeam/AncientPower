package de.zsgn.ancientpower;

import java.util.Iterator;
import java.util.List;

import de.zsgn.ancientpower.blocks.BlockLiquidEnergy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.ItemFluidContainer;

public class ItemChargeableCrystal extends ItemFluidContainer {
    public final static ItemChargeableCrystal INSTANCE=new ItemChargeableCrystal();
    public final static String NAME="chargeablecrystal";
    
    public final static int CAPACITY=3000;
    public final static int RANGE=3;
    
    
    protected ItemChargeableCrystal() {
        super(0,CAPACITY);
        this.setUnlocalizedName(AncientPower.MODID+"."+NAME);
        this.setCreativeTab(AncientPower.proxy.creativeTab);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn,
            EntityPlayer playerIn) {
        BlockPos start=playerIn.getPosition().add(RANGE, RANGE, RANGE);
        BlockPos end=playerIn.getPosition().add(-RANGE, -RANGE, -RANGE);
        Iterator toscan=BlockPos.getAllInBox(start, end).iterator();
        while(toscan.hasNext()){
            BlockPos pos=(BlockPos)toscan.next();
            if(worldIn.getBlockState(pos).getBlock().equals(BlockLiquidEnergy.instance)){
                int amounttodrain=fill(itemStackIn, BlockLiquidEnergy.instance.drain(worldIn, pos, false), false);
                if(amounttodrain>0&&amounttodrain<=BlockLiquidEnergy.instance.drain(worldIn, pos, false).amount){
                    fill(itemStackIn, BlockLiquidEnergy.instance.drain(worldIn, pos, true), true);
                }
            }
        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }
    @Override
    public boolean canItemEditBlocks()
    {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn,
            List tooltip, boolean advanced) {
        int amount=0;
        if(getFluid(stack)!=null){
           amount=getFluid(stack).amount;
        }
        tooltip.add(amount+"/"+capacity);
    }

}
