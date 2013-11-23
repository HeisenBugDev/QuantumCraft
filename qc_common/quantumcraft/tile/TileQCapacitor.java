package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import quantumcraft.net.Location;
import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileQCapacitor extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 100000;
    }

    @Override
    public int guiID() {
        return 7;
    }

    @Override
    public void onBlockBreak() {

    }

    public int requestQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            updateNextTick = true;
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            updateNextTick = true;
            return e;
        }
    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
                64.0D;
    }

}
