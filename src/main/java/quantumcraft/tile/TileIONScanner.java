package quantumcraft.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.scheduler.IONScannerScheduler;

public class TileIONScanner extends TileEnergySink {

    private IONScannerScheduler scheduler = new IONScannerScheduler(100, this);
    private int xLoop = 0;

    @Override
    public int getMaxEnergy() {
        return 100;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    private void scan() {
        scheduler.resetHarvesters();
        if (xLoop >= 30) {
            xLoop = 0;
        } else xLoop++;
        for (int z = -30; z <= 30; z++) {
            for (int y = -30; y <= 30; y++) {
                Block thisBlock = BasicUtils.getBlockInstance(worldObj, xCoord + xLoop, yCoord + y, zCoord + z);
                if (thisBlock != null) {
                    String name = OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(thisBlock)));
                    if (name.contains("ore")) {
                        scheduler.add(xCoord + xLoop, yCoord + y, zCoord + z);

                    }
                    TileEntity te = worldObj.getBlockTileEntity(xCoord + xLoop, yCoord + y, zCoord + z);

                    if (te != null) {
                        if (te instanceof TileIONHarvester) {
                            scheduler.addHarvester((TileIONHarvester) te);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        if (this.getCurrentEnergy() > 0) {
            // Requires a constant feed of power.
            subtractEnergy(1);
            if (worldObj.getWorldTime() % 20 == 0) {
                scan();
            }
            scheduler.checkHarvesters(worldObj);
            scheduler.run();
        }
    }
}
