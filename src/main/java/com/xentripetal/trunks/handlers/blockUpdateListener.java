package com.xentripetal.trunks.handlers;

import javax.annotation.Nullable;

import com.xentripetal.trunks.TrunkManager;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;

/**
 * Listener for blockupdates to check if a sapling has turned into a log block.
 * Adds block to trunk manager and runs if so
 *
 * @author Xentripetal (xen@xentripetal.com)
 */
public class blockUpdateListener implements IWorldEventListener {

	private TrunkManager trunkManager;

	public blockUpdateListener(TrunkManager trunkManager) {
		this.trunkManager = trunkManager;
	}

	public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
		if ( TrunkManager.isVanillaLog(newState.getBlock()) && TrunkManager.isSapling(oldState.getBlock())) {
			trunkManager.replace(pos, worldIn);
		}
	}

	public void notifyLightSet(BlockPos pos) {
	}

	/**
	 * On the client, re-renders all blocks in this range, inclusive. On the
	 * server, does nothing.
	 */
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
	}

	public void playSoundToAllNearExcept(@Nullable EntityPlayer player, SoundEvent soundIn, SoundCategory category,
			double x, double y, double z, float volume, float pitch) {
	}

	public void playRecord(SoundEvent soundIn, BlockPos pos) {
	}

	public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord,
			double xSpeed, double ySpeed, double zSpeed, int... parameters) {
	}

	public void spawnParticle(int p_190570_1_, boolean p_190570_2_, boolean p_190570_3_, double p_190570_4_,
			double p_190570_6_, double p_190570_8_, double p_190570_10_, double p_190570_12_, double p_190570_14_,
			int... p_190570_16_) {
	}

	/**
	 * Called on all IWorldAccesses when an entity is created or loaded. On
	 * client worlds, starts downloading any necessary textures. On server
	 * worlds, adds the entity to the entity tracker.
	 */
	public void onEntityAdded(Entity entityIn) {
	}

	/**
	 * Called on all IWorldAccesses when an entity is unloaded or destroyed. On
	 * client worlds, releases any downloaded textures. On server worlds,
	 * removes the entity from the entity tracker.
	 */
	public void onEntityRemoved(Entity entityIn) {
	}

	public void broadcastSound(int soundID, BlockPos pos, int data) {
	}

	public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {
	}

	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
	}
}
