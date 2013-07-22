package mods.quantumcraft.blocks;

import mods.quantumcraft.core.Config;
import mods.quantumcraft.core.Loader;
import mods.quantumcraft.render.RenderOre;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import java.util.Random;

public class BlockOreQuantonium extends BlockOre {

    public BlockOreQuantonium(int par1) {
        super(par1);
        this.setHardness(2.5F);
    }

    private Icon iconore;
    private Icon iconbase;

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return Loader.ItemRawQuantonium.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1 + par1Random.nextInt(4);
    }

    public Icon getIconbase() {
        return iconbase;
    }

    public Icon getIconore() {
        return iconore;
    }
}
