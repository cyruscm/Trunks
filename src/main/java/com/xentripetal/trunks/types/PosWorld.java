package com.xentripetal.trunks.types;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A simple tuple object for storing a position and world
 *
 * @author Xentripetal (xen@xentripetal.com)
 */
public class PosWorld {

	private BlockPos pos;
	private World world;

	public PosWorld(BlockPos posIn, World worldIn) {
		pos = posIn;
		world = worldIn;
	}

	public World getWorld() {
		return world;
	}

	public BlockPos getPos() {
		return pos;
	}
}
