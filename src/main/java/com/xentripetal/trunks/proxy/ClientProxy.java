package com.xentripetal.trunks.proxy;

public class ClientProxy extends ServerProxy {
	public void preInit() {
		System.out.println("Client PreInit");
	}

	public void init() {
		System.out.println("Client Init");
	}

	public void postInit() {
		System.out.println("Client PostInit");
	}
}
