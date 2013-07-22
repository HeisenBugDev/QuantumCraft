package mods.quantumcraft.net;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;

public class RecursiveScanner {
	public interface IDataGatherer{
		public void gatherDataOnTile(World w, Location l);
	}
	
	public static void scan(World w, Location l, IDataGatherer dataGatherer){
		List<Location> memory = new ArrayList<Location>();
		scan(w, l, dataGatherer, memory);
	}
	
	public static void scan(World w, Location l, IDataGatherer dataGatherer, List<Location> memory){
		
	}
}