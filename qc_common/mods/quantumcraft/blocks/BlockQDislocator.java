package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockMachine;
import mods.quantumcraft.machine.TileQEInjector;
import mods.quantumcraft.util.BasicUtils;
import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import mods.quantumcraft.machine.TileQDislocator;
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

public class BlockQDislocator extends BlockMachine {


    public BlockQDislocator(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDislocator();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDS_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDS_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDS_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDS_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQDS_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDS_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDS_back");
    }

}