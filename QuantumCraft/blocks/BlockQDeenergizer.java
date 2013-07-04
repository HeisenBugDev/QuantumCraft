package mods.quantumcraft.blocks;

import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.TileQDeenergizer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockQDeenergizer extends BlockRotatable{

	public BlockQDeenergizer(int par1) {
		super(par1, Material.rock);
	}

	Icon top, front, back, side, bottom, temp;

	/*
	 * 0 == bottom 1 == top 2 == north 3 == south 4 == west 5 == east
	 */
	public Icon getIcon(int side, int meta) {
		if (side == 3)
			return this.front;
		else
			return this.bottom;
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	public TileEntity createNewTileEntity(World par1World) {
		return new TileQDeenergizer();
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.temp = Block.stone.getIcon(0, 0);
		this.top = par1IconRegister.registerIcon("QuantumCraft:machineQDE_top");
		this.front = par1IconRegister
				.registerIcon("QuantumCraft:machineQDE_front");
		this.back = par1IconRegister
				.registerIcon("QuantumCraft:machineQDE_back");
		this.side = par1IconRegister
				.registerIcon("QuantumCraft:machineQDE_side");
		this.bottom = par1IconRegister
				.registerIcon("QuantumCraft:machineQDE_bottom");
	}


	public boolean onBlockActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
			float par9) {
		par5EntityPlayer.openGui(QuantumCraft.instance, 1, par1World, x, y, z);
		return true;
	}
}
