package com.karbij.dinnersmp.datagen;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.item.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DinnerSMP.MODID , existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.FINALMEAL_ITEM);
        simpleItem(ModItems.FINALDRINK);
        simpleItem(ModItems.NICEPLATE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
            return withExistingParent(item.getId().getPath(), 
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DinnerSMP.MODID, "item/" + item.getId().getPath()));
    }
    

}
