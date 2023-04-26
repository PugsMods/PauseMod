package com.pugzarecute.pausemod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public interface MobEffectMixin {

    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;tickEffects()V", at = @At("HEAD"),cancellable = true)
    private void tick(CallbackInfo ci){
            if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")) ci.cancel();
    }

    @Inject(method = "setHealth(F)V", at = @At("HEAD"),cancellable = true)
    private void setHealth(float pHealth, CallbackInfo ci){
        if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")) ci.cancel();
    }


    @Redirect(at=@At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"),method = "travel(Lnet/minecraft/world/phys/Vec3;)V")
    private void travel(LivingEntity instance, double v1, double v2, double v3){

        if(instance.hasEffect(MobEffects.LEVITATION)){
            Vec3 vec325 = instance.handleRelativeFrictionAndCalculateMovement(pTravelVector, f2);
            float f2 = instance.level.getBlockState( instance.getBlockPosBelowThatAffectsMyMovement()).getFriction(level, this.getBlockPosBelowThatAffectsMyMovement(), this);
            if(instance.shouldDiscardFriction()) v2 -=  (0.05D * (double)(instance.getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - vec35.y) * 0.2D;
        }
    }
}
