package com.xentripetal.trunks;

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

	/**
	 * Replaces the block at the provided position and world
	 * if the block is a valid log.
	 * @param worldIn World of block
	 * @param pos Position of block
	 * @return True if replaced
	 */
	public static boolean replaceLog(BlockPos pos, World worldIn) {
		return replaceLog(pos, worldIn, worldIn.getBlockState(pos));
	}


	/**
	 * Replaces the block at the provided position and world
	 * if the block is a valid log.
	 * @param worldIn World of block
	 * @param pos Position of block
	 * @param state Block at pos state
	 * @return True if replaced
	 */
	public static boolean replaceLog(BlockPos pos, World worldIn, IBlockState state) {
		IBlockState newState = trunkReplacement(state);
		if (newState != null) {
			worldIn.setBlockState(pos, state, 3);
			return true;
		}
		return false;
	}


	/**
	 * Takes the provided state and generates a state for the
	 * corresponding trunk type.
	 * Returns null if not a valid log.
	 * @param state State of block to check log
	 * @return State of new trunk block
	 */
	private static IBlockState trunkReplacement(IBlockState state) {
		IBlockState toReturn = null;
		if (Block.isEqualTo(state.getBlock(), Blocks.LOG)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockOldLog.VARIANT).getMetadata());
		} else if (Block.isEqualTo(state.getBlock(), Blocks.LOG2)) {
			toReturn = ModBlocks.TRUNK.getStateFromMeta(state.getValue(BlockNewLog.VARIANT).getMetadata() + 4);
		}
		return toReturn;
	}

	public static boolean isValidLog(Block block) {
		return isVanillaLog(block);
	}

	public static boolean isVanillaLog(Block block) {
		return (Block.isEqualTo(block, Blocks.LOG) || Block.isEqualTo(block, Blocks.LOG2));
	}

	public static boolean isValidSapling(Block block) {
		return isVanillaSapling(block);
	}

	public static boolean isVanillaSapling(Block block) {
		return Block.isEqualTo(block, Blocks.SAPLING);
	}

}
