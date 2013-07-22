package mods.quantumcraft.net;

import net.minecraft.nbt.NBTTagCompound;

public class Location{
	private int x, y, z;
	
	public Location(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Saves this Location to NBT
	 * @return An NBTTagCompound containing this Location's data.
	 */
	public NBTTagCompound write(){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("x", x);
		nbt.setInteger("y", y);
		nbt.setInteger("z", z);
		return nbt;
	}
	
	/**
	 * Reads a Location from NBT
	 * @param nbt NBTTagCompound containing the Location's data.
	 * @return a Location object in case of success, null otherwise.
	 */
	public static Location read(NBTTagCompound nbt){
		Location location = null;
		if(nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z")){
			int x = nbt.getInteger("x");
			int y = nbt.getInteger("y");
			int z = nbt.getInteger("z");
			location = new Location(x, y, z);
		}
		return location;
	}
	
	public int getXCoord(){
		return x;
	}
	
	public int getYCoord(){
		return y;
	}
	
	public int getZCoord(){
		return z;
	}
}