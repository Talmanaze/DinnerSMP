package com.karbij.dinnersmp.block.entity.client;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.entity.FinalMeal_Entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FinalMealModel extends GeoModel<FinalMeal_Entity> {

    @Override
    public ResourceLocation getModelResource(FinalMeal_Entity animatable) {
        return new ResourceLocation(DinnerSMP.MODID, "geo/finalmeal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FinalMeal_Entity animatable) {
        return new ResourceLocation(DinnerSMP.MODID, "textures/block/finalmeal_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FinalMeal_Entity animatable) {
        return new ResourceLocation(DinnerSMP.MODID, "animations/final_meal.animation.json");
    }

}
