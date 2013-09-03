package mods.quantumcraft.tile;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QDeelectrifierInitPacket;
import mods.quantumcraft.tile.abstracttiles.TileEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 9/2/13
 * Time: 3:29 PM
 */
public class TileQDeelectrifier extends TileEnergySource implements IPowerReceptor{
    private int tickCounter = 0;
    private boolean redstonePower = false;
    private int energyBuffer = 1000;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int getCurrentEnergy() {
        return 0;
    }

    @Override
    public int subtractEnergy(int req) {
        energyBuffer -= req;
        if (energyBuffer < 0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }

    @Override
    public int guiID() {
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.energyBuffer = par1NBTTagCompound.getInteger("energyBuffer");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("energyBuffer", this.energyBuffer);

        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {

    }

    @Override
    public Packet getDescriptionPacket() {
        QDeelectrifierInitPacket packet = PacketHandler.getPacket(QDeelectrifierInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }

    @Override
    public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side) {
        return null;
    }

    @Override
    public void doWork(PowerHandler workProvider) {

    }

    @Override
    public World getWorld() {
        return null;
    }
}
