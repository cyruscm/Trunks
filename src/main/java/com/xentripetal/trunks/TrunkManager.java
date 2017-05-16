package com.xentripetal.trunks;

import java.util.LinkedList;
import java.util.Queue;

import com.xentripetal.trunks.blocks.BlockTrunk;
import com.xentripetal.trunks.types.PosWorld;

import net.minecraft.block.state.IBlockState;
import com.xentripetal.trunks.Blocks;
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
			replaceTree(posWorld.getWorld(), posWorld.getPos());
			return true;
		}
		return false;
	}
	
	private void replaceTree(World worldIn, BlockPos pos) {
		IBlockState state = Blocks.TRUNK.getDefaultState().withProperty(BlockTrunk.VARIANT, BlockTrunk.EnumType.VARIANT_A);
		worldIn.setBlockState(pos, state);
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
