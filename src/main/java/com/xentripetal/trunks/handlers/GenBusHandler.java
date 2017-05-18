package com.xentripetal.trunks.handlers;

import java.util.Random;

import com.xentripetal.trunks.TrunkManager;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


/**
 * Registers events for detecting sapling growth and tree generation
 *
 * @author Xentripetal (xen@xentripetal.com)
 */
public class GenBusHandler {

	private TrunkManager trunkManager;

	public GenBusHandler(TrunkManager trunkManager) {
		this.trunkManager = trunkManager;
	}

	/**
	 * Removed and switched to only using the blockUpdateListener
	 * 
	 * Adds position on a saplinggrowtree to trunk manager.
	 * This is not perfect and has misreports half the time on a 
	 * invalid sapling growth. So must be paired with blockUpdateListener
	 * @param e event
	@SubscribeEvent
	public void saplingGrowTreeEvent(SaplingGrowTreeEvent e) {
		if (e.getResult() != Result.DENY) {
			trunkManager.add(e.getPos(), e.getWorld());
		}
	}
	*/

	/**
	 * on a tree placement, override and repeat steps with the tree
	 * pos added to Trunk Manager.
	 *
	 * The BIG_SHROOM event runs after Tree event in a chunk generation. 
	 *
	 * @param e event
	 */
	@SubscribeEvent
	public void decorateBiomeEvent(DecorateBiomeEvent.Decorate e) {
		if (e.getType().equals(DecorateBiomeEvent.Decorate.EventType.TREE)) {
			Random random = e.getRand();
			World worldIn = e.getWorld();
			BlockPos chunkPos = e.getPos();
			Biome biomeIn = worldIn.getBiome(chunkPos);
			int k1 = biomeIn.theBiomeDecorator.treesPerChunk;
			for (int j2 = 0; j2 < k1; ++j2) {
				int k6 = random.nextInt(16) + 8;
				int l = random.nextInt(16) + 8;
				WorldGenAbstractTree worldgenabstracttree = biomeIn.genBigTreeChance(random);
				worldgenabstracttree.setDecorationDefaults();
				BlockPos blockpos = worldIn.getHeight(chunkPos.add(k6, 0, l));

				if (worldgenabstracttree.generate(worldIn, random, blockpos)) {
					trunkManager.add(blockpos, worldIn);
					worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
				}
			}
			e.setResult(Result.DENY);
		} else if (e.getType().equals(DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM)) {
			trunkManager.process();
		}
	}
}
