package com.karbij.dinnersmp.datagen;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DinnerSMP.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.FINALMEAL_BLOCK);
        blockWithItem(ModBlocks.TEETH_BLOCK);

        //simpleBlock(ModBlocks.FINALMEAL_BLOCK.get(), 
        //    new ModelFile.UncheckedModelFile((modLoc("block/final_meal"))));

    }
    

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
