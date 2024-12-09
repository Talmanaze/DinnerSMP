package com.karbij.dinnersmp.block.entity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.collect.Sets;
import com.karbij.dinnersmp.DinnerSMP;
import com.karbij.dinnersmp.block.custom.FinalMealBlock;
import com.karbij.dinnersmp.block.entity.util.TickableBlockEntity;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.server.ServerLifecycleHooks;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import javax.annotation.Nullable;

public class FinalMeal_Entity extends BlockEntity implements GeoBlockEntity, TickableBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
	private static int tickCounter;

	MinecraftServer sourceServer = ServerLifecycleHooks.getCurrentServer();

	final int RANGE = 5;
	BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
	BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
	AABB box = new AABB(topCorner, bottomCorner);

	public FinalMeal_Entity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.FINAL_MEAL_ENTITY.get(), pos, state);
	}

	// We just want a permanent idle animation happening here
	// But if it's day time we want him to take a nap
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "ritual_controller", state -> {
				return state.setAndContinue(DefaultAnimations.IDLE);
			}).triggerableAnim("interact", DefaultAnimations.INTERACT)
			.setCustomInstructionKeyframeHandler( state -> {
				return;
			} ));
	}

	public void reviveNearest() {
        assert this.level != null;
        Entity nearestNoOpSpectator = this.level.getNearestPlayer(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), 6, (entity) ->{
			return ((ServerPlayer) entity).isSpectator();
		} );
		/*
		double distanceToSpectator = getDistnaceBetweenTwoWorldPos(nearestNoOpSpectatorPos, this.worldPosition);
		List<Entity> entities = this.level.getEntities(null, box);
		for(Entity target : entities) {
			if (target instanceof Player) {
				if(((Player)target).isSpectator()) {
					double newDistance = getDistnaceBetweenTwoWorldPos(nearestNoOpSpectatorPos, target.blockPosition());
					if (newDistance < distanceToSpectator) {
						System.out.println("Entity found: " + target.getName());
						distanceToSpectator = newDistance;
						nearestNoOpSpectator = target;
					}
				}
			}
		}
		*/
		if(!(nearestNoOpSpectator == null)) {
			if (nearestNoOpSpectator instanceof ServerPlayer) {
				ServerPlayer serverNoOp = ((ServerPlayer)nearestNoOpSpectator);
				serverNoOp.setPos(new Vec3(this.worldPosition.getX(), this.worldPosition.getY() + .5, this.worldPosition.getZ()));
				serverNoOp.setGameMode(GameType.SURVIVAL);
				serverNoOp.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 4));
			 }
		}
	}

	


	private double getDistnaceBetweenTwoWorldPos(BlockPos first, BlockPos second) {
		double x1 = first.getX();
		double y1 = first.getY();
		double z1 = first.getZ();

		double x2 = second.getX();
		double y2 = second.getY();
		double z2 = second.getZ();

		double d1 = (x2 - x1);
		double d2 = (y2-y1);
		double d3 = (z2-z1);

		double d4 = Math.sqrt((d1*d1) + (d2*d2) + (d3*d3));

		return d4;
	}

	public void removeSelf(){
		this.level.setBlock(this.worldPosition, Blocks.AIR.defaultBlockState(), 11);
	}
	public void startRitual() {
		if(!(tickCounter>0)) {
			tickCounter = 1;
			List<Entity> entities = this.level.getEntities(null, box);
			for (Entity target : entities) {
				if (target instanceof LivingEntity)
					((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 160, 4));
					((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 4));
				target.hurt(this.level.damageSources().magic(), 2);
			}
		}
	}



	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

	@Override
	public void tick() {
		if (this.level == null || this.level.isClientSide())
			return;
		//System.out.println("Hello from \"tick\"");
		if (tickCounter > 0) tickCounter += 1;

		if (tickCounter >= 140) {
			System.out.println("tickCounter: " + tickCounter);
			tickCounter = 0;
			reviveNearest();
			this.level.explode(null, ((double) this.worldPosition.getX()), ((double) this.worldPosition.getY()), ((double) this.worldPosition.getZ()), 6.0F, Level.ExplosionInteraction.TNT);
			removeSelf();
		}
	}
}
