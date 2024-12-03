package com.karbij.dinnersmp.datagen.loot;

import java.util.Set;

import com.karbij.dinnersmp.block.ModBlocks;
import com.karbij.dinnersmp.item.ModItems;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    
    @Override
    protected void generate(){
        
        dropSelf(ModBlocks.FINALMEAL_BLOCK.get());

        this.add(ModBlocks.TEETH_BLOCK.get(), 
            block -> createTeethBlockLikeDrops(ModBlocks.TEETH_BLOCK.get(), ModItems.NICEPLATE.get()));
    }

    protected LootTable.Builder createLikeSingleItemTable(ItemLike pItem) {
      return LootTable.lootTable().withPool(this.applyExplosionCondition(pItem, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem))));
   }



   protected LootTable.Builder createTeethBlockLikeDrops(Block pBlock, Item item) {
      return createSilkTouchDispatchTable(pBlock,
        this.applyExplosionDecay(pBlock,
            LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
   }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
