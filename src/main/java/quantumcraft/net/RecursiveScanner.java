package quantumcraft.net;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import quantumcraft.util.Location;

import java.util.ArrayList;
import java.util.List;

class RecursiveScanner {
    public interface IDataGatherer {
        public void gatherDataOnTile(World w, Location l);
    }

    public static void scan(World w, Location l, IDataGatherer dataGatherer) {
        List<Location> memory = new ArrayList<Location>();
        scan(w, l, dataGatherer, memory);
    }

    public static void scan(World w, Location l, IDataGatherer dataGatherer, List<Location> memory) {
        if (l == null || memory.contains(l)) return;
        Block block = Block.blocksList[w.getBlockId(l.getXCoord(), l.getYCoord(), l.getZCoord())];
        if (block instanceof IQEnergyComponent) {
            memory.add(l);
            dataGatherer.gatherDataOnTile(w, l);
            Location[] connections = ((IQEnergyComponent) block).getPossibleConnections(w, l);
            for (Location c : connections) {
                Block peer = Block.blocksList[w.getBlockId(c.getXCoord(), c.getYCoord(), c.getZCoord())];
                if (peer instanceof IQEnergyComponent) {
                    Location[] peerconns = ((IQEnergyComponent) peer).getPossibleConnections(w, c);
                    for (Location p : peerconns) {
                        if (p.equals(l)) {
                            scan(w, c, dataGatherer, memory);
                        }
                    }
                }
            }
        }
    }
}