package mods.quantumcraft.blocks;

import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.TileQDeenergizer;
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

public class BlockQDeenergizer extends BlockContainer {

	private Icon iconFront;
	private Icon iconDefault;

	public BlockQDeenergizer(int id) {
		super(id, Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileQDeenergizer();
	}

	public void registerIcons(IconRegister iconRegister) {
		iconFront = iconRegister.registerIcon("QuantumCraft:machineQDE_front");
		iconDefault = iconRegister.registerIcon("QuantumCraft:machineQDE_side");
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (meta == 0 && side == 3)
			return iconFront;
		else
			return iconDefault;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLiving entityLiving, ItemStack itemStack) {

		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if ((entity == null) || (!(entity instanceof TileQDeenergizer)))
			return;

		byte facing = 0;
		int facingI = MathHelper
				.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (facingI == 0) {
			facing = 2;
		}
		if (facingI == 1) {
			facing = 5;
		}
		if (facingI == 2) {
			facing = 3;
		}
		if (facingI == 3) {
			facing = 4;
		}

		System.out.println("metadata!");
		world.setBlockMetadataWithNotify(x, y, z, facing, 2);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer entityPlayer, int par6, float par7, float par8,
			float par9) {
		entityPlayer.openGui(QuantumCraft.instance, 1, world, x, y, z);
		return true;
	}
}