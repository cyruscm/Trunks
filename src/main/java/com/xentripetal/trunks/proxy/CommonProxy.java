package com.xentripetal.trunks.proxy;

import com.xentripetal.trunks.handlers.EventBusHandler;
import com.xentripetal.trunks.handlers.GenBusHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {

	}

	public void init(FMLInitializationEvent e) {
		MinecraftForge.TERRAIN_GEN_BUS.register(new GenBusHandler());
		MinecraftForge.EVENT_BUS.register(new EventBusHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {
	}
}
