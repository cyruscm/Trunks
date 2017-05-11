package com.xentripetal.trunks.proxy;

import com.xentripetal.trunks.handlers.TerrainHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("Common PreInit");
		MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainHandler());


	}

	public void init(FMLInitializationEvent e) {
		System.out.println("Common Init");
	}

	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("Common PostInit");
	}
}
