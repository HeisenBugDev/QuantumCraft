package quantumcraft.blocks;

import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
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
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        super.registerBlockIcons(par1IconRegister);
        Loader.IconLoader.loadAll(par1IconRegister);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Loader.ItemRawQuantonium;
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
