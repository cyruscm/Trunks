package com.xentripetal.trunks.handlers;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.xentripetal.trunks.TrunkManager;

public class EventBusHandler {
	
	private TrunkManager trunkManager;
	
	public EventBusHandler(TrunkManager trunkManager) {
		this.trunkManager = trunkManager;
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		e.getWorld().addEventListener(new blockUpdateListener(trunkManager));
	}
}
