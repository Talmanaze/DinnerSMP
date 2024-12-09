package com.karbij.dinnersmp;

import com.karbij.dinnersmp.block.ModBlocks;
import com.karbij.dinnersmp.block.entity.ModBlockEntities;
import com.karbij.dinnersmp.block.entity.client.FinalMealRenderer;
import com.karbij.dinnersmp.item.ModCreativeModTabs;
import com.karbij.dinnersmp.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.GeckoLib;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DinnerSMP.MODID)
public class DinnerSMP
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "dinnersmp";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public DinnerSMP(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        //ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);       

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.FINALMEAL_ITEM);
            event.accept(ModItems.FINALDRINK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            BlockEntityRenderers.register(ModBlockEntities.FINAL_MEAL_ENTITY.get(), FinalMealRenderer::new);
        }
    }
/*
    private static final String PROTOCOL_VERSION = "1.0";
	public static SimpleChannel channel = NetworkRegistry.ChannelBuilder
		.named(resourceLocation("main"))
		.clientAcceptedVersions(PROTOCOL_VERSION::equals)
		.serverAcceptedVersions(PROTOCOL_VERSION::equals)
		.networkProtocolVersion(() -> PROTOCOL_VERSION)
		.simpleChannel();
	
    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MODID, path);
    }
	@SubscribeEvent
	public static void setUp(FMLCommonSetupEvent event) {
		channel.messageBuilder(RitualListMessage.class, 0)
			.encoder(RitualListMessage::write)
			.decoder(RitualListMessage::new)
			.consumerMainThread(RitualListMessage::handle)
			.add();
	}
*/
    
    
}
