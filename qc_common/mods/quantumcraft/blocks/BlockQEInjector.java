package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockEnergySink;
import mods.quantumcraft.util.BasicUtils;
import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockQEInjector extends BlockEnergySink {

    public BlockQEInjector(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQEInjector();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEI_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEI_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEI_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEI_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEI_back");
    }

}