package com.karbij.dinnersmp.block.entity.client;

import com.karbij.dinnersmp.block.entity.FinalMeal_Entity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FinalMealRenderer extends GeoBlockRenderer<FinalMeal_Entity>{
    public FinalMealRenderer(BlockEntityRendererProvider.Context context) {
        super(new FinalMealModel());
    }

    @Override
    public boolean shouldRenderOffScreen(FinalMeal_Entity pBlockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(FinalMeal_Entity pBlockEntity, Vec3 pCameraPos) {
        return super.shouldRender(pBlockEntity, pCameraPos);
    }
}
