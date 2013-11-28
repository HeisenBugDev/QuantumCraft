package quantumcraft.fluid;

import net.minecraftforge.fluids.Fluid;

public class FluidSteam extends Fluid{
    public static final FluidSteam fluid = new FluidSteam();

    public FluidSteam() {
        super("Steam");
        setUnlocalizedName("Steam");
    }
}
