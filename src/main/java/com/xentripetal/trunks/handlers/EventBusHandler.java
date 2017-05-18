package com.xentripetal.trunks.handlers;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Registers the blockUpdateListener to forge
 *
 * @author Xentripetal (xen@xentripetal.com)
 */
public class EventBusHandler {

	public EventBusHandler() {
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		e.getWorld().addEventListener(new blockUpdateListener());
	}
}
