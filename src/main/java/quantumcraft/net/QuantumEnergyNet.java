package quantumcraft.net;

import debughandler.DebugHandler;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import quantumcraft.core.QuantumCraft;
import quantumcraft.net.RecursiveScanner.IDataGatherer;
import quantumcraft.tile.TileQCapacitor;

import java.util.ArrayList;
import java.util.List;

public class QuantumEnergyNet {
    private static class SourcePropagationDG implements IDataGatherer {
        private Location source;

        public SourcePropagationDG(Location src) {
            this.source = src;
        }

        @Override
        public void gatherDataOnTile(World w, Location l) {
            int id = w.getBlockId(l.getXCoord(), l.getYCoord(), l.getZCoord());
            Block b = Block.blocksList[id];
            if (b instanceof IQEnergySink) {
                ((IQEnergySink) b).addSourceToList(w, l, source);
            }
        }
    }

    private static class SourceScanningDG implements IDataGatherer {
        private List<Location> sources = new ArrayList<Location>();

        @Override
        public void gatherDataOnTile(World w, Location l) {
            int id = w.getBlockId(l.getXCoord(), l.getYCoord(), l.getZCoord());
            Block b = Block.blocksList[id];
            if (b instanceof IQEnergySource) {
                sources.add(l);
            }
        }

        public List<Location> getList() {
            return sources;
        }

    }

    private static class ChangedLinkDG implements IDataGatherer {
        private List<Location> sources = new ArrayList<Location>();
        private List<Location> sinks = new ArrayList<Location>();

        @Override
        public void gatherDataOnTile(World w, Location l) {
            int id = w.getBlockId(l.getXCoord(), l.getYCoord(), l.getZCoord());
            Block b = Block.blocksList[id];
            if (b instanceof IQEnergySource) {
                sources.add(l);
            }
            if (b instanceof IQEnergySink) {
                sinks.add(l);
            }
        }

        public void addAllSourcesToAllSinks(World w) {
            for (Location sink : sinks) {
                List<Location> sources2 = new ArrayList<Location>(sources);
                List<Location> sourcesTmp = new ArrayList<Location>();
                for (Location source : sources2) {
                    if (sink.compareCoords(source)) {
                        sourcesTmp.add(source);
                        //sources2.remove(source);
                    }
                    if (w.getBlockTileEntity(sink.getXCoord(), sink.getYCoord(),
                            sink.getZCoord()) instanceof TileQCapacitor &&
                            w.getBlockTileEntity(source.getXCoord(), source.getYCoord(),
                                    source.getZCoord()) instanceof TileQCapacitor) {
                        sourcesTmp.add(source);
                        QuantumCraft.logHandler.debugPrint("In QuantumEnergy Net, sink and source are capacitors");
                        QuantumCraft.logHandler.debugPrint(w.getBlockTileEntity(sink.getXCoord(),sink.getYCoord(),
                                sink.getZCoord()), "Is the sink");
                        QuantumCraft.logHandler.debugPrint(w.getBlockTileEntity(source.getXCoord(),
                                source.getYCoord(),source.getZCoord()), "Is the source");
                    }
                }
                for (Location source : sourcesTmp) {
                    sources2.remove(source);
                }
                int id = w.getBlockId(sink.getXCoord(), sink.getYCoord(), sink.getZCoord());
                Block b = Block.blocksList[id];
                if (b instanceof IQEnergySink) {
                    ((IQEnergySink) b).replaceSourceList(w, sink, new EnergySourceList(sources2));
                }
            }
        }

    }

    public static void propagateSourceLocation(World w, Location l) {
        SourcePropagationDG dataGatherer = new SourcePropagationDG(l);
        RecursiveScanner.scan(w, l, dataGatherer);
    }

    public static List<Location> getSourceLocations(World w, Location l) {
        SourceScanningDG dataGatherer = new SourceScanningDG();
        RecursiveScanner.scan(w, l, dataGatherer);
        return dataGatherer.getList();
    }

    public static void onAddedLink(World w, Location l) {
        onChangedLink(w, new Location[]{l});
    }

    public static void onChangedLink(World w, Location[] tips) {
        QuantumCraft.logHandler.debugPrint("[QuantumEnergyNet] Changed link detected");
        List<Location> memory = new ArrayList<Location>();
        for (Location tip : tips) {
            ChangedLinkDG dataGatherer = new ChangedLinkDG();
            RecursiveScanner.scan(w, tip, dataGatherer, memory);
            dataGatherer.addAllSourcesToAllSinks(w);
        }
    }
}