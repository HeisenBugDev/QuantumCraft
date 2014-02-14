package quantumcraft.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import debughandler.DebugRegistry;
import debughandler.LogHandler;
import net.minecraftforge.common.MinecraftForge;
import quantumcraft.core.network.ChannelHandler;
import quantumcraft.render.BlockHighlighterHandler;

import java.util.EnumMap;

@Mod(modid = "quantumcraft", name = "QuantumCraft", version = "@VERSION@", dependencies = "after:BuildCraft|Silicon")
public class QuantumCraft {

    EnumMap<Side, FMLEmbeddedChannel> channels =
            NetworkRegistry.INSTANCE.newChannel(Config.modNetworkChannel, new ChannelHandler());
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
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ClientProxy());
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

