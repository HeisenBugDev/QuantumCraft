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

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Loader.ItemRawQuantonium;
    }

    @Override
    public IIcon getIcon(int i, int b) {
        System.out.println("returning icon");
        return Loader.IconLoader.quantonium_ore_base;
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        Loader.IconLoader.loadAll(p_149651_1_);
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
