package quantumcraft.core;


import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int cX, int cZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider) {
        generateOres(world, random, cX, cZ);
    }

    private void generateOres(World world, Random random, int cX, int cZ) {
        WorldGenMinable quantonium = new WorldGenMinable(Loader.OreQuantonium.blockID, 5);
        quantonium.generate(world, random, (cX * 16) + random.nextInt(16), 8 + random.nextInt(56),
                (cZ * 16) + random.nextInt(16));
        quantonium.generate(world, random, (cX * 16) + random.nextInt(16), 8 + random.nextInt(56),
                (cZ * 16) + random.nextInt(16));
        WorldGenMinable unbioxenium = new WorldGenMinable(Loader.OreUnbioxenium.blockID, 5);
        unbioxenium.generate(world, random, (cX * 16) + random.nextInt(16), 8 + random.nextInt(56),
                (cZ * 16) + random.nextInt(16));
        unbioxenium.generate(world, random, (cX * 16) + random.nextInt(16), 8 + random.nextInt(56),
                (cZ * 16) + random.nextInt(16));
    }
}