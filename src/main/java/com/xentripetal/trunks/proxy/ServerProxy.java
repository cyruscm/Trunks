package com.xentripetal.trunks.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("Server PreInit");

	}

	public void init(FMLInitializationEvent e) {
		System.out.println("Server Init");
	}

	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("Server PostInit");
	}
}
