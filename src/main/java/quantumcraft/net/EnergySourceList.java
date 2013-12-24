package quantumcraft.net;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import quantumcraft.util.Coords;

import java.util.ArrayList;
import java.util.List;

public class EnergySourceList {
    private List<Coords> sources = new ArrayList<Coords>();

    public EnergySourceList() {
    }

    public EnergySourceList(List<Coords> src) {
        sources.addAll(src);
    }

    public int requestQuantumEnergy(World w, int request) {
        int retrieved = 0;
        for (Coords source : sources) {
            int id = w.getBlockId(source.getXCoord(), source.getYCoord(), source.getZCoord());
            Block b = Block.blocksList[id];
            if (b instanceof IQEnergySource) {
                retrieved += ((IQEnergySource) b).requestQuantumEnergy(w, source, request - retrieved);
            }
        }
        return retrieved;
    }

    public int size(){
        return sources.size();
    }

    public void addSource(Coords src) {
        sources.add(src);
    }

    public static EnergySourceList read(NBTTagCompound nbt) {
        EnergySourceList esl = new EnergySourceList();
        if (nbt.hasKey("size")) {
            int sz = nbt.getInteger("size");
            for (int i = 0; i < sz; i++) {
                if (nbt.hasKey("location_" + i)) {
                    NBTTagCompound data = nbt.getCompoundTag("location_" + i);
                    Coords location = Coords.read(data);
                    if (location != null) esl.addSource(location);
                }
            }
        }
        return esl;
    }

    public NBTTagCompound write() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("size", sources.size());
        for (int i = 0; i < sources.size(); i++) {
            Coords source = sources.get(i);
            nbt.setCompoundTag("location_" + i, source.write());
        }
        return nbt;
    }
}
