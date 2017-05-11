package com.xentripetal.trunks.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.xentripetal.trunks.handlers.TerrainHandler;

public class ServerProxy extends CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		System.out.println("Server PreInit");
	}

	public void init(FMLInitializationEvent e) {
		super.init(e);
		System.out.println("Server Init");
	}

	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		System.out.println("Server PostInit");
	}
}
