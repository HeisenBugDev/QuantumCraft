package quantumcraft.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.scheduler.IONScannerScheduler;

public class TileIONScanner extends TileEnergySink {

    private IONScannerScheduler scheduler = new IONScannerScheduler(100, this);
    private int xLoop = -30;

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
        if (xLoop >= 30) {
            xLoop = -30;
        } else xLoop++;
        for (int z = -30; z <= 30; z++) {
            for (int y = -30; y <= 30; y++) {
                Block thisBlock = BasicUtils.getBlockInstance(worldObj, xCoord + xLoop, yCoord + y, zCoord + z);
                if (thisBlock != null) {
                    String name = OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(thisBlock)));
                    if (name.contains("ore")) {
                        scheduler.add(xCoord + xLoop, yCoord + y, zCoord + z);
                    }
                    TileEntity te = worldObj.getTileEntity(xCoord + xLoop, yCoord + y, zCoord + z);

                    if (te != null) {
                        if (te instanceof TileIONHarvester) {
                            if (!scheduler.getHarvesters().contains(te)) scheduler.addHarvester((TileIONHarvester) te);
                        }
                    }
                }
            }
        }
    }

    @Override public String getStatusText() {
        if (this.getCurrentEnergy() < 5) return EnumChatFormatting.RED + "Need power!";
        EnumChatFormatting harvesterStatusColor =
                ((scheduler.getHarvesters().size() > 0) ? EnumChatFormatting.GREEN : EnumChatFormatting.RED);
        return EnumChatFormatting.GREEN + "" + scheduler.getBlocks().size() + " Ores " + harvesterStatusColor +
                scheduler.getHarvesters().size() + " IOHs";
    }

    public String getSpeed() {
        if (scheduler.getHarvesters().size() > 0)
            return EnumChatFormatting.GREEN + "" + (100 / scheduler.getHarvesters().size()) + " ticks/b";
        return EnumChatFormatting.RED + "0 blocks/t";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        if (this.getCurrentEnergy() > 0) {
            // Requires a constant feed of power.
            subtractEnergy(4);
            if (worldObj.getWorldTime() % 20 == 0) {
                scan();
            }
            scheduler.checkHarvesters(worldObj);
            scheduler.run();
        }
    }
}
