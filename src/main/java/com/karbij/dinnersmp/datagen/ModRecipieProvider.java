package com.karbij.dinnersmp.datagen;

import java.util.function.Consumer;

import com.karbij.dinnersmp.block.ModBlocks;
import com.karbij.dinnersmp.item.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipieProvider extends RecipeProvider implements IConditionBuilder{

    public ModRecipieProvider(PackOutput pOutput) {
        super(pOutput);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FINALMEAL_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', ModItems.FINALMEAL_ITEM.get())
            .unlockedBy(getHasName(ModItems.NICEPLATE.get()), has(ModItems.NICEPLATE.get()))
            .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NICEPLATE.get(), 7)
            .requires(ModBlocks.TEETH_BLOCK.get())
            .unlockedBy(getHasName(ModBlocks.TEETH_BLOCK.get()), has(ModBlocks.TEETH_BLOCK.get()))
            .save(pWriter);
    }
}
