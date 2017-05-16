package com.xentripetal.trunks;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.xentripetal.trunks.blocks.BlockTrunk;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

/**
 * 
 * @author choonster, xentripetal
 *
 */
@SuppressWarnings("WeakerAccess")
@ObjectHolder(References.MODID)
public class ModBlocks {

	public static final BlockTrunk TRUNK = new BlockTrunk(Material.WOOD);


	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
					TRUNK,
			};

			registry.registerAll(blocks);

		}


		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					new ItemMultiTexture(TRUNK, TRUNK, TRUNK::getName)
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}
		}
	}

}
