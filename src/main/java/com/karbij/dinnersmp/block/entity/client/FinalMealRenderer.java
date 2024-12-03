package com.karbij.dinnersmp.block.entity.client;

import com.karbij.dinnersmp.block.entity.FinalMeal_Entity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FinalMealRenderer extends GeoBlockRenderer<FinalMeal_Entity>{
    public FinalMealRenderer(BlockEntityRendererProvider.Context context) {
        super(new FinalMealModel());
    }

}
