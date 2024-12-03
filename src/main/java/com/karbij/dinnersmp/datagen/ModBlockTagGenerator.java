package com.karbij.dinnersmp.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagGenerator extends BlockTagsProvider {
    
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, DinnerSMP.MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.NEEDS_STONE_TOOL)
            .add(ModBlocks.TEETH_BLOCK.get());
        
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.TEETH_BLOCK.get());

    }
    

}
