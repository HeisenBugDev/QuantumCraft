package mods.QuantumCraft.blocks;

import mods.QuantumCraft.core.QuantumCraft;
import mods.QuantumCraft.machine.TileQDeenergizer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockQDeenergizer extends BlockContainer {

	public BlockQDeenergizer(int par1) {
		super(par1, Material.rock);
		// TODO Auto-generated constructor stub
	}

	Icon top, front, side, bottom, temp;
	
    public Icon getIcon(int par1, int par2)
    {
        return temp;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileQDeenergizer();
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.temp = Block.stone.getIcon(0, 0);
    	this.top = par1IconRegister.registerIcon("QuantumCraft:machineQDE_top");
        this.front = par1IconRegister.registerIcon("QuantumCraft:machineQDE_front");
        this.side = par1IconRegister.registerIcon("QuantumCraft:machineQDE_side");
        this.bottom = par1IconRegister.registerIcon("QuantumCraft:machineQDE_bottom");
    }
    
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	par5EntityPlayer.openGui(QuantumCraft.instance, 1, par1World, x, y, z);
    	return true;
    }
}
