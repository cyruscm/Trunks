package com.xentripetal.trunks.proxy;

import com.xentripetal.trunks.Blocks;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		System.out.println("Client PreInit");

	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		System.out.println("Client Init");
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		System.out.println("Client PostInit");
	}
}
