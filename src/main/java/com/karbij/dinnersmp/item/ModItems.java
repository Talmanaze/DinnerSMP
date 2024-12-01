package com.karbij.dinnersmp.item;

import java.rmi.registry.Registry;

import com.karbij.dinnersmp.DinnerSMP;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, DinnerSMP.MODID);

    public static final RegistryObject<Item> FINALMEAL = ITEMS.register("finalmeal",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINALDRINK = ITEMS.register("finaldrink",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NICEPLATE = ITEMS.register("niceplate",
        () -> new Item(new Item.Properties()));
        
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}