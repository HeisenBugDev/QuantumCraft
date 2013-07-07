package mods.quantumcraft.core;

import net.minecraft.item.ItemStack;

public class QDERecipe {

	private ItemStack _inputItem;
	private ItemStack _outputItem;
	private int _energyValue;
	private int _processTime;
	
	public ItemStack getInputItem()
	{
		return _inputItem;
	}
	
	public ItemStack getOutputItem()
	{
		return _outputItem;
	}
	
	public int getEnergyValue()
	{
		return _energyValue;
	}
	
	public int getProcessTime()
	{
		return _processTime;
	}
	
	/**
	 * Full constructor
	 * @param inputItem - Input Item in the recipe.
	 * @param outputItem - Output Item in the recipe.
	 * @param energyValue - QEnergy Value of the item, Quantonium = 100
	 * @param processTime - Time to process the item (ticks)
	 */
	public QDERecipe(ItemStack inputItem, ItemStack outputItem, int energyValue, int processTime)
	{
		this._inputItem = inputItem;
		this._outputItem = outputItem;
		this._energyValue = energyValue;
		this._processTime = processTime;
	}
	
	/**
	 * Default process time (60 ticks = 3s)
	 */
	public QDERecipe(ItemStack inputItem, ItemStack outputItem, int energyValue)
	{
		this._inputItem = inputItem;
		this._outputItem = outputItem;
		this._energyValue = energyValue;
		this._processTime = 60; //3 seconds
	}
	/**
	 * OutputItem = Depleted Crystal
	 * @param inputItem
	 * @param energyValue
	 */
	public QDERecipe(ItemStack inputItem, int energyValue)
	{
		this._inputItem = inputItem;
		this._outputItem = new ItemStack(Loader.ItemDepletedCrystal);
		this._energyValue = energyValue;
		this._processTime = 60; //3 seconds
	}
	
}
