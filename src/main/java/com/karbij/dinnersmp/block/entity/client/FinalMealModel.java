package com.karbij.dinnersmp.block.entity.client;

import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.entity.FinalMeal_Entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class FinalMealModel extends DefaultedBlockGeoModel<FinalMeal_Entity> {


    public FinalMealModel() {
        super(new ResourceLocation(DinnerSMP.MODID, "finalmeal"));
    }

}
