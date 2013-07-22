package mods.quantumcraft.net;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EnergySourceList{
	private List<Location> sources = new ArrayList<Location>();
	
	public EnergySourceList(){}
	
	public EnergySourceList(List<Location> src){
		sources.addAll(src);
	}
	
	public int getQuantumEnergy(World w, int request){
		int retrieved = 0;
		for(Location source : sources){
			int id = w.getBlockId(source.getXCoord(), source.getYCoord(), source.getZCoord());
			Block b = Block.blocksList[id];
			if(b instanceof IQEnergySource){
				retrieved += ((IQEnergySource)b).getQuantumEnergy(request - retrieved);
			}
		}
		return retrieved;
	}
	
	public void addSource(Location src){
		sources.add(src);
	}
	
	public static EnergySourceList read(NBTTagCompound nbt){
		EnergySourceList esl = new EnergySourceList();
		if(nbt.hasKey("size")){
			int sz = nbt.getInteger("size");
			for(int i = 0; i < sz; i++){
				if(nbt.hasKey("location_"+i)){
					NBTTagCompound data = nbt.getCompoundTag("location_"+i);
					Location location = Location.read(data);
					if(location != null) esl.addSource(location);
				}
			}
		}
		return esl;
	}
	
	public NBTTagCompound write(){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("size", sources.size());
		for(int i = 0; i < sources.size(); i++){
			Location source = sources.get(i);
			nbt.setCompoundTag("location_"+i, source.write());
		}
		return nbt;
	}
}
