package com.pugzarecute.pausemod.mixin;

import com.pugzarecute.pausemod.Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;



@Mixin(LivingEntity.class)
public class MobEffectMixin {
    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", at = @At("HEAD"),cancellable = true)
    private void addEffect(MobEffectInstance pEffectInstance, CallbackInfoReturnable<Boolean> cir){
        if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")&& !pEffectInstance.getEffect().equals(Registry.TIME_STOPPED.get())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", at = @At("HEAD"),cancellable = true)
    private void addEffect(MobEffectInstance pEffectInstance, Entity pEntity, CallbackInfoReturnable<Boolean> cir){
        if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether") && !pEffectInstance.getEffect().equals(Registry.TIME_STOPPED.get())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
    @Inject(method = "Lnet/minecraft/world/entity/LivingEntity;tickEffects()V", at = @At("HEAD"),cancellable = true)
    private void tickEffects(CallbackInfo ci){
            if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")) ci.cancel();
    }

    @Inject(method = "setHealth(F)V", at = @At("HEAD"),cancellable = true)
    private void setHealth(float pHealth, CallbackInfo ci){
        if(((LivingEntity) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")) ci.cancel();
    }
}
