package com.xentripetal.trunks;

import java.util.LinkedList;
import java.util.Queue;

import com.xentripetal.trunks.types.PosWorld;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrunkManager {

	private Queue<PosWorld> treeCoords;
	private boolean processing;
	
	public TrunkManager() {
		treeCoords = new LinkedList<PosWorld>();
	}
	
	public boolean add(BlockPos pos, World worldIn) {
		return treeCoords.offer(new PosWorld(pos, worldIn));
	}
	
	private boolean processFirstTree() {
		PosWorld posWorld = treeCoords.poll();
		if (posWorld != null) {
			posWorld.getWorld().setBlockToAir(posWorld.getPos());
			return true;
		}
		return false;
	}
	
	public void process() {
		if (!processing) {
			processing = true;
			while (processFirstTree()) {
			}
				
			processing = false;
		}
	}
}
