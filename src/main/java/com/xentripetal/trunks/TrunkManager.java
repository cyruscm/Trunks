package com.xentripetal.trunks;

import java.util.LinkedList;
import java.util.Queue;

import com.xentripetal.trunks.blocks.BlockTrunk;
import com.xentripetal.trunks.types.PosWorld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
		IBlockState state = trunkReplacement(worldIn.getBlockState(pos));
		if (state != null) {
			worldIn.setBlockState(pos, state, 3);
		}
	}
	
	private IBlockState trunkReplacement(IBlockState state) {
		IBlockState toReturn = null;
		if (Block.isEqualTo(state.getBlock(), Blocks.LOG)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockOldLog.VARIANT).getMetadata());
		} else if (Block.isEqualTo(state.getBlock(), Blocks.LOG2)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockNewLog.VARIANT).getMetadata() + 4);
		}
		return toReturn;
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
