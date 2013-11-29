package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;
import quantumcraft.fluid.FluidSteam;
import quantumcraft.tile.abstracttiles.TileMachineBase;

public class TileSteamGenerator extends TileMachineBase implements IFluidHandler, IInventory {
    private FluidTank tank = new FluidTank(12000);
    private ItemStack[] inventory = new ItemStack[2];

    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public int guiID() {
        // Will be 8 when done
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public int fill(ForgeDirection forgeDirection, FluidStack fluidStack, boolean b) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection forgeDirection, FluidStack fluidStack, boolean b) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection forgeDirection, int i, boolean b) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection forgeDirection, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection forgeDirection, Fluid fluid) {
        return fluid.getID() == FluidSteam.fluid.getID();
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection forgeDirection) {
        return new FluidTankInfo[]{tank.getInfo()};
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.inventory[i] != null) {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j) {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            } else {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0) {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack stack = inventory[i];
        inventory[i] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory[i] = itemStack;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        // This may need to be like the more complicated on in TileIONForge.java
        return true;
    }

    @Override public String getInvName() { return "Steam Generator"; }

    @Override public boolean isInvNameLocalized() { return false; }

    @Override public int getInventoryStackLimit() { return 64; }

    @Override public void openChest() {}

    @Override public void closeChest() {}

}
