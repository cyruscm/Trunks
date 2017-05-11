package com.xentripetal.trunks.proxy;

public class ServerProxy implements CommonProxy {
	public void preInit() {
		System.out.println("Server PreInit");

	}

	public void init() {
		System.out.println("Server Init");
	}

	public void postInit() {
		System.out.println("Server PostInit");
	}
}
