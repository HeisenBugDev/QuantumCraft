package quantumcraft.util.scheduler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.tile.TileIONHarvester;

import java.util.ArrayList;
import java.util.List;

public class IONScannerScheduler extends BlockBreakerScheduler {
    private List<TileIONHarvester> harvesters = new ArrayList<TileIONHarvester>();
    private List<TileIONHarvester> removeHarvestersQueue = new ArrayList<TileIONHarvester>();
    private int useOften = 0;
    private int harvesterIterator = 0;

    public IONScannerScheduler(int oftenSet, TileEntity tileSet) {
        super(oftenSet, tileSet);
    }

    public void addHarvester(TileIONHarvester harvester) {
        harvesters.add(harvester);
    }

    public void resetHarvesters() {
        harvesters.clear();
    }

    public List<TileIONHarvester> getHarvesters() {
        return harvesters;
    }

    public void removeHarvestersQueueAdd(TileIONHarvester te) {
        removeHarvestersQueue.add(te);
    }

    public void removeHarvesters() {
        if (removeHarvestersQueue.size() > 0) {
            for (TileIONHarvester te : removeHarvestersQueue) {
                harvesters.remove(te);
            }
            removeHarvestersQueue.clear();
        }
    }

    @Override
    public void run() {
        if (harvesters.size() != 0) {
            useOften = often / harvesters.size();
        }
        if (iteration < useOften) {
            iteration++;
        } else {
            iteration = 0;
            process();
        }
    }

    public void checkHarvesters(World worldObj) {
        if (iteration >= useOften) {
            for (TileIONHarvester te : getHarvesters()) {
                if (worldObj.getBlockTileEntity(te.xCoord, te.yCoord, te.zCoord) == null) {
                    removeHarvestersQueueAdd(te);
                }
            }
            removeHarvesters();
        }
    }

    @Override
    public boolean breakBlock(int x, int y, int z) {
        harvesterIterator++;
        if (harvesterIterator >= harvesters.size()) harvesterIterator = 0;
        if (harvesters.size() > 0) {
            if (harvesters.get(harvesterIterator).breakBlock(x, y, z)) {
                return true;
            }
        }
        return false;
    }
}
