package com.xentripetal.trunks.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("Client PreInit");

	}

	@Override
	public void init(FMLInitializationEvent e) {
		System.out.println("Client Init");
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("Client PostInit");
	}
}
