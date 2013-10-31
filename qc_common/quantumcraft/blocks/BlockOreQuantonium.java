package quantumcraft.blocks;

import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import quantumcraft.core.Loader;
import quantumcraft.render.RenderOre;

import java.util.Random;

public class BlockOreQuantonium extends BlockOre {

    public BlockOreQuantonium(int par1) {
        super(par1);
        setHardness(2.5F);
        setResistance(1.0F);
    }

    public void setIcon(Icon i) {
        this.blockIcon = i;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        Loader.IconLoader.loadAll(par1IconRegister);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return Loader.ItemRawQuantonium.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1 + par1Random.nextInt(4);
    }

    @Override
    public int getRenderType() {
        return RenderOre.instance().getRenderId();
    }
}
