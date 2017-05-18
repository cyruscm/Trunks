package com.xentripetal.trunks;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xentripetal.trunks.blocks.BlockTrunk;
import com.xentripetal.trunks.types.PosWorld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Takes in positions and replaces with the corresponding trunk block
 *
 * @author Xentripetal (xen@xentripetal.com)
 */
public class TrunkManager {
	private static final Log log =
		LogFactory.getLog(TrunkManager.class);


	private Queue<PosWorld> treeCoords;
	private boolean processing;

	public TrunkManager() {
		treeCoords = new LinkedList<PosWorld>();
	}

	public static boolean replaceLog(BlockPos pos, World worldIn) {
	
		return false;
	}

	public static boolean isVanillaLog(Block block) {
		return (Block.isEqualTo(block, Blocks.LOG) || Block.isEqualTo(block, Blocks.LOG2));
	}

	public static boolean isSapling(Block block) {
		return Block.isEqualTo(block, Blocks.SAPLING);
	}


	/**
	 * Adds the provided pos and worldIn to the queue.
	 * Note that the boolean does not validate if the pos
	 * is actually a log block. It represents the internal
	 * data structures offer response.
	 * @param pos Position of block to replace
	 * @param worldIn World of block to replace
	 * @return Boolean if position was accepted
	 */
	public boolean add(BlockPos pos, World worldIn) {
		return treeCoords.offer(new PosWorld(pos, worldIn));
	}

	/**
	 * Handles the replacement of the first tree in queue.
	 * @return True if there was a first tree, false if not
	 */
	private boolean processFirstTree() {
		PosWorld posWorld = treeCoords.poll();
		if (posWorld != null) {
			replaceTree(posWorld.getWorld(), posWorld.getPos());
			return true;
		}
		return false;
	}


	/**
	 * Replaces the block at the provided position and world
	 * if the block is a valid log.
	 * @param worldIn World of block
	 * @param pos Position of block
	 */
	private void replaceTree(World worldIn, BlockPos pos) {
		IBlockState state = trunkReplacement(worldIn.getBlockState(pos));
		if (state != null) {
			worldIn.setBlockState(pos, state, 3);
		}
	}

	/**
	 * Takes the provided state and generates a state for the
	 * corresponding trunk type.
	 * Returns null if not a valid log.
	 * @param state State of block to check log
	 * @return State of new trunk block
	 */
	private IBlockState trunkReplacement(Block block) {
		IBlockState toReturn = null;
		if (Block.isEqualTo(state., Blocks.LOG)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockOldLog.VARIANT).getMetadata());
		} else if (Block.isEqualTo(state.getBlock(), Blocks.LOG2)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockNewLog.VARIANT).getMetadata() + 4);
		}
		return toReturn;
	}


	/**
	 * Runs through all currently queued trees and replaces them
	 */
	public void process() {
		if (!processing) {
			processing = true;
			while (processFirstTree()) {
			}

			processing = false;
		}
	}
}
