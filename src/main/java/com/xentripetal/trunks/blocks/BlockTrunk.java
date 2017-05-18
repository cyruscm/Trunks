package com.xentripetal.trunks.blocks;

import java.util.Random;

import com.xentripetal.trunks.References;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Variant Trunk block containing variants for all types of default logs.
 * Note that it does not maintain rotation.
 *  
 * @author Choonster, Xentripetal
 */
public class BlockTrunk extends Block {
	public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create(
			"variant", BlockPlanks.EnumType.class);

	public BlockTrunk(Material materialIn) {
		super(materialIn, materialIn.getMaterialMapColor());
		setRegistryName(References.MODID, "trunk");
		setUnlocalizedName(getRegistryName().toString());
		this.setHardness(6.0F);
		this.setSoundType(SoundType.WOOD);
		this.setHarvestLevel("axe", 1);

	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	/**
	 * Converts the metadata to the valid block for default logs.
	 *
	 * @param state
	 * @param rand
	 * @param fortune
	 * @return
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (state.getValue(VARIANT).getMetadata() < 4)
			return Item.getItemFromBlock(Blocks.LOG);
		return Item.getItemFromBlock(Blocks.LOG2);
	}

	/**
	 * Doesn't seem like this will run, but the default logblock has it
	 */
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
			for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
				IBlockState iblockstate = worldIn.getBlockState(blockpos);

				if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
					iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
				}
			}
		}
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).getMetadata();
	}

	/**
	 * Returns the variant of block to drop. It is converted to regular
	 * minecraft trunks, so a mod 4 is done to match the 2 variants
	 * @param state
	 * @return
	 */
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMetadata() % 4;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (final BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
			list.add(new ItemStack(this, 1, enumType.getMetadata()));
		}
	}

	public static void setBlockName(Block block, String blockName) {
		block.setRegistryName(References.MODID, blockName);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}

	/**
	 * Get the unlocalised name suffix for the specified {@link ItemStack}.
	 *
	 * @param stack The ItemStack
	 * @return The unlocalised name suffix
	 */
	public String getName(ItemStack stack) {
		final int metadata = stack.getMetadata();

		return BlockPlanks.EnumType.byMetadata(metadata).getName();
	}

}
