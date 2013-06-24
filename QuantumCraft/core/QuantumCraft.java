package mods.QuantumCraft.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="QuantumCraft", name="Quantum Craft", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class QuantumCraft {
	
        @Instance("QuantumCraft")
        public static QuantumCraft instance;

        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        	Config.loadConfig();
        }
       
        @Init
        public void load(FMLInitializationEvent event) {
        	Loader.initAll();
        }
       
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                
        }
}