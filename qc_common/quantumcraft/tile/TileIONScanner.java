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
    private int iteration = 0;

    @Override
    public int getMaxEnergy() {
        return 1000;
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
        for (int x = -30; x <= 30; x++) {
            for (int z = -30; z <= 30; z++) {
                for (int y = -30; y <= 30; y++) {
                    Block thisBlock = BasicUtils.getBlockInstance(worldObj, xCoord + x, yCoord + y, zCoord + z);
                    if (thisBlock != null) {
                        String name = OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(thisBlock)));
                        if (name.contains("ore")) {
                            scheduler.add(xCoord + x, yCoord + y, zCoord + z);

                        }
                        TileEntity te = worldObj.getBlockTileEntity(xCoord + x, yCoord + y, zCoord + z);

                        if (te != null) {
                            if (te instanceof TileIONHarvester) {
                                scheduler.addHarvester((TileIONHarvester) te);
                            }
                        }
                    }
                }

            }
        }
    }

    @Override
    public void updateEntity() {
        if (iteration < 100) {
            iteration++;
        } else {
            iteration = 0;
            scan();
        }
        scheduler.checkHarvesters(worldObj);
        scheduler.run();
    }
}
