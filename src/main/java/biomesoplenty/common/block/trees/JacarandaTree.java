/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class JacarandaTree extends AbstractTreeGrower
{
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      if (random.nextInt(10) == 0)
      {
         return flowers ? BOPTreeFeatures.BIG_JACARANDA_TREE_BEES : BOPTreeFeatures.BIG_JACARANDA_TREE;
      }
      else
      {
         return flowers ? BOPTreeFeatures.JACARANDA_TREE_BEES : BOPTreeFeatures.JACARANDA_TREE;
      }
   }
}