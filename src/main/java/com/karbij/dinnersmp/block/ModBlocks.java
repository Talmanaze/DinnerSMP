package com.karbij.dinnersmp.block;

import java.util.function.Supplier;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.custom.FinalMealBlock;
import com.karbij.dinnersmp.item.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, DinnerSMP.MODID);

    public static final RegistryObject<Block> TEETH_BLOCK = registerBlock("teeth_block",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
            RegistryObject<T> toReturn = BLOCKS.register(name, block);
            registerBlockItem(name, toReturn);
            return toReturn;
        }

        private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
            return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public static final RegistryObject<Block> FINALMEAL_BLOCK = registerBlock("finalmeal_block",
        () -> new FinalMealBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

        
        public static void register(IEventBus eventBus) {
            BLOCKS.register(eventBus);
        }

        
}
