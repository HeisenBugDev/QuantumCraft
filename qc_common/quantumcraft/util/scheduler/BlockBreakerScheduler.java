package quantumcraft.util.scheduler;

import net.minecraft.tileentity.TileEntity;
import quantumcraft.core.Coords;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakerScheduler extends Scheduler {
    private List<Coords> blocks = new ArrayList<Coords>();
    private TileEntity tile;

    public BlockBreakerScheduler(int oftenSet, TileEntity tileSet) {
        super(oftenSet);
        tile = tileSet;
    }

    @Override
    public void process() {
        if (blocks.size() > 0) {
            int x = blocks.get(0).x;
            int y = blocks.get(0).y;
            int z = blocks.get(0).z;
            breakBlock(x, y, z);
            blocks.remove(0);
        }
    }

    public void add(int x, int y, int z) {
        blocks.add(new Coords(x, y, z));
    }

    public void breakBlock(int x, int y, int z) {
        tile.worldObj.setBlockToAir(x, y, z);
    }

}
