package com.karbij.dinnersmp.block.entity;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.ModBlocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DinnerSMP.MODID);

        public static final RegistryObject<BlockEntityType<FinalMeal_Entity>> FINAL_MEAL_ENTITY = 
            BLOCK_ENTITIES.register("final_meal_entity", () ->
                BlockEntityType.Builder.of(FinalMeal_Entity::new,
                    ModBlocks.FINALMEAL_BLOCK.get()).build(null));

        public static void register(IEventBus eventBus) {
            BLOCK_ENTITIES.register(eventBus);
        }
}
