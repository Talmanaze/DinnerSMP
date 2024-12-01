package com.karbij.dinnersmp.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;
import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DinnerSMP.MODID);

    public static final RegistryObject<CreativeModeTab> DINNERSMP_TAB = CREATIVE_MODE_TABS.register("dinner_tab", 
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FINALMEAL.get()))
            .title(Component.translatable("creativetab.dinner_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.FINALDRINK.get());
                pOutput.accept(ModItems.FINALMEAL.get());
                pOutput.accept(ModItems.NICEPLATE.get());
                pOutput.accept(ModBlocks.TEETH_BLOCK.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
