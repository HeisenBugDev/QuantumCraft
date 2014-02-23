package quantumcraft.net;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import quantumcraft.util.Coords;

import java.util.ArrayList;
import java.util.List;

class RecursiveScanner {
    public interface IDataGatherer {
        public void gatherDataOnTile(World w, Coords l);
    }

    public static void scan(World w, Coords l, IDataGatherer dataGatherer) {
        List<Coords> memory = new ArrayList<Coords>();
        scan(w, l, dataGatherer, memory);
    }

    public static void scan(World w, Coords l, IDataGatherer dataGatherer, List<Coords> memory) {
        if (l == null || memory.contains(l)) return;
        Block block = w.getBlock(l.getXCoord(),l.getYCoord(),l.getZCoord());
        if (block instanceof IQEnergyComponent) {
            memory.add(l);
            dataGatherer.gatherDataOnTile(w, l);
            Coords[] connections = ((IQEnergyComponent) block).getPossibleConnections(w, l);
            for (Coords c : connections) {
                Block peer = w.getBlock(c.getXCoord(),c.getYCoord(),c.getZCoord());
                if (peer instanceof IQEnergyComponent) {
                    Coords[] peerconns = ((IQEnergyComponent) peer).getPossibleConnections(w, c);
                    for (Coords p : peerconns) {
                        if (p.equals(l)) {
                            scan(w, c, dataGatherer, memory);
                        }
                    }
                }
            }
        }
    }
}