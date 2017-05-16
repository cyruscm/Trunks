package com.xentripetal.trunks.blocks;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import com.xentripetal.trunks.References;
import com.xentripetal.trunks.util.IVariant;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
 * A block with several variants.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2594064-metadata-blocks-dont-have-textures
 *
 * @author Choonster, Xentripetal
 */
public class BlockTrunk extends Block {
	public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

	public BlockTrunk(Material materialIn) {
		super(materialIn, materialIn.getMaterialMapColor());
		setRegistryName(References.MODID, "trunk");
		setUnlocalizedName(getRegistryName().toString());
        this.setHardness(6.0F);
        this.setSoundType(SoundType.WOOD);
		
	}
	
    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        System.out.println(state.getProperties().toString());
        Comparable<?> type = state.getProperties().get(VARIANT);
        for (EnumType variants : EnumType.values()) {
        	if (type.equals(variants)) {
        		return variants.getItem();
        	}
        }
    	return null;
    }
	
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = 4;
        int j = 5;

        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
        {
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
            {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                {
                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                }
            }
        }
    }
    
    @Override public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos){ return true; }
    @Override public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos){ return true; }


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 1;
	}


	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (final EnumType enumType : EnumType.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
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

		return EnumType.byMetadata(metadata).getName();
	}

	public enum EnumType implements IVariant {
		VARIANT_A(0, "oak", Item.getItemFromBlock(Blocks.LOG)),
		VARIANT_B(1, "birch", Item.getItemFromBlock(Blocks.LOG2));

		private static final EnumType[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

		private final int meta;
		private final String name;
		private final Item item;

		EnumType(int meta, String name, Item item) {
			this.meta = meta;
			this.name = name;
			this.item = item;
		}

		public int getMeta() {
			return meta;
		}
		
		public Item getItem() {
			return item;
		}

		@Override
		public String getName() {
			return name;
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}
	}
}
