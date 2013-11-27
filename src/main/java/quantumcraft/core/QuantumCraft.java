package quantumcraft.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import quantumcraft.core.network.PacketHandler;

@Mod(modid = "QuantumCraft", name = "Quantum Craft", version = "@VERSION@", dependencies = "after:BuildCraft|Silicon")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {Config.modNetworkChannel},
        packetHandler = PacketHandler.class)
public class QuantumCraft {

    @SidedProxy(clientSide = "quantumcraft.core.ClientProxy", serverSide = "quantumcraft.core.CommonProxy")
    public static CommonProxy proxy;
    @Instance("QuantumCraft")
    public static QuantumCraft instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(this, new ClientProxy());
        Config.initConfig(event);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        Loader.initAll();
        System.out.println("[QuantumCraft] Finished loading.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
