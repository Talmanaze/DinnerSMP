package com.karbij.dinnersmp.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class FinalMeal_Entity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public FinalMeal_Entity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.FINAL_MEAL_ENTITY.get(), pos, state);
	}

	// We just want a permanent idle animation happening here
	// But if it's day time we want him to take a nap
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, state -> {
//			if (getLevel().getDayTime() > 23000 || getLevel().getDayTime() < 13000) {
//				return state.setAndContinue(DefaultAnimations.REST);
//			}
//			else {
				return state.setAndContinue(DefaultAnimations.IDLE);
//			}
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

}
