package quantumcraft.net;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import quantumcraft.core.QuantumCraft;
import quantumcraft.net.RecursiveScanner.IDataGatherer;
import quantumcraft.tile.TileQuantumCapacitor;
import quantumcraft.util.Coords;

import java.util.ArrayList;
import java.util.List;

public class QuantumEnergyNet {
    private static class SourcePropagationDG implements IDataGatherer {
        private Coords source;

        public SourcePropagationDG(Coords src) {
            this.source = src;
        }

        @Override
        public void gatherDataOnTile(World w, Coords l) {
            Block b = w.getBlock(l.getXCoord(), l.getYCoord(), l.getZCoord());
            if (b instanceof IQEnergySink) {
                ((IQEnergySink) b).addSourceToList(w, l, source);
            }
        }
    }

    private static class SourceScanningDG implements IDataGatherer {
        private List<Coords> sources = new ArrayList<Coords>();

        @Override
        public void gatherDataOnTile(World w, Coords l) {
            Block b = w.getBlock(l.getXCoord(), l.getYCoord(), l.getZCoord());
            if (b instanceof IQEnergySource) {
                sources.add(l);
            }
        }

        public List<Coords> getList() {
            return sources;
        }

    }

    private static class ChangedLinkDG implements IDataGatherer {
        private List<Coords> sources = new ArrayList<Coords>();
        private List<Coords> sinks = new ArrayList<Coords>();

        @Override
        public void gatherDataOnTile(World w, Coords l) {
            Block b = w.getBlock(l.getXCoord(), l.getYCoord(), l.getZCoord());
            if (b instanceof IQEnergySource) {
                sources.add(l);
            }
            if (b instanceof IQEnergySink) {
                sinks.add(l);
            }
        }

        public void addAllSourcesToAllSinks(World w) {
            for (Coords sink : sinks) {
                List<Coords> sources2 = new ArrayList<Coords>(sources);
                List<Coords> sourcesTmp = new ArrayList<Coords>();
                for (Coords source : sources2) {
                    if (sink.compareCoords(source)) {
                        sourcesTmp.add(source);
                        //sources2.remove(source);
                    }
                    if (w.getTileEntity(sink.getXCoord(), sink.getYCoord(),
                            sink.getZCoord()) instanceof TileQuantumCapacitor &&
                            w.getTileEntity(source.getXCoord(), source.getYCoord(),
                                    source.getZCoord()) instanceof TileQuantumCapacitor) {
                        sourcesTmp.add(source);
                        QuantumCraft.logHandler.debugPrint("In QuantumEnergy Net, sink and source are capacitors");
                        QuantumCraft.logHandler.debugPrint(w.getTileEntity(sink.getXCoord(),sink.getYCoord(),
                                sink.getZCoord()), "Is the sink");
                        QuantumCraft.logHandler.debugPrint(w.getTileEntity(source.getXCoord(),
                                source.getYCoord(),source.getZCoord()), "Is the source");
                    }
                }
                for (Coords source : sourcesTmp) {
                    sources2.remove(source);
                }
                Block b = w.getBlock(sink.getXCoord(), sink.getYCoord(), sink.getZCoord());
                if (b instanceof IQEnergySink) {
                    ((IQEnergySink) b).replaceSourceList(w, sink, new EnergySourceList(sources2));
                }
            }
        }

    }

    public static void propagateSourceLocation(World w, Coords l) {
        SourcePropagationDG dataGatherer = new SourcePropagationDG(l);
        RecursiveScanner.scan(w, l, dataGatherer);
    }

    public static List<Coords> getSourceLocation(World w, Coords l) {
        SourceScanningDG dataGatherer = new SourceScanningDG();
        RecursiveScanner.scan(w, l, dataGatherer);
        return dataGatherer.getList();
    }

    public static void onAddedLink(World w, Coords l) {
        onChangedLink(w, new Coords[]{l});
    }

    public static void onChangedLink(World w, Coords[] tips) {
        QuantumCraft.logHandler.debugPrint("[QuantumEnergyNet] Changed link detected");
        List<Coords> memory = new ArrayList<Coords>();
        for (Coords tip : tips) {
            ChangedLinkDG dataGatherer = new ChangedLinkDG();
            RecursiveScanner.scan(w, tip, dataGatherer, memory);
            dataGatherer.addAllSourcesToAllSinks(w);
        }
    }
}