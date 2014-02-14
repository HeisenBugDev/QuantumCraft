package quantumcraft.blocks;

import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import quantumcraft.core.Loader;
import quantumcraft.render.RenderOre;

import java.util.Random;

public class BlockOreQuantonium extends BlockOre {

    public BlockOreQuantonium() {
        setHardness(2.5F);
        setResistance(1.0F);
        setLightLevel(0.3F);
    }

    public void setIcon(IIcon i) {
        this.blockIcon = i;
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
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
