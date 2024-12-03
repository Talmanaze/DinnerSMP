package com.karbij.dinnersmp.datagen;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import com.karbij.dinnersmp.DinnerSMP;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagGenerator extends ItemTagsProvider{

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                                CompletableFuture<TagLookup<Block>> _p275322_, @Nullable ExistingFileHelper existingFileHelper){
                                super(p_275343_, p_275729_, _p275322_, DinnerSMP.MODID, existingFileHelper);
    }


    @Override
    protected void addTags(Provider pProvider) {
        
    }

}
