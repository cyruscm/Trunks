package com.xentripetal.trunks.types;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
