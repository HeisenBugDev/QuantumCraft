package quantumcraft.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import debughandler.DebugRegistry;
import debughandler.LogHandler;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import quantumcraft.core.network.PacketHandler;
import quantumcraft.render.BlockHighlighterHandler;

@Mod(modid = "QuantumCraft", name = "Quantum Craft", version = "@VERSION@", dependencies = "after:BuildCraft|Silicon")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {Config.modNetworkChannel},
        packetHandler = PacketHandler.class)
public class QuantumCraft {

    @SidedProxy(clientSide = "quantumcraft.core.ClientProxy", serverSide = "quantumcraft.core.CommonProxy")
    public static CommonProxy proxy;
    @Instance("QuantumCraft")
    public static QuantumCraft instance;
    public static LogHandler logHandler = new LogHandler("Quantumcraft");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        DebugRegistry.addLogHandler(logHandler);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockHighlighterHandler());
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

    @ForgeSubscribe @SideOnly(Side.CLIENT)
    public void beforeTextureStitch(TextureStitchEvent.Pre event) {
        logHandler.debugPrint("preStitch called");
    }
}
