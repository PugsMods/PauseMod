package com.pugzarecute.pausemod.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V",at = @At("HEAD"),cancellable = true)
    private void exhaust(float pExhaustion, CallbackInfo ci){
        if(((Player) (Object) this).level.dimension().location().toString().equals("minecraft:the_nether")) ci.cancel();
    }

    @Inject(method="Lnet/minecraft/world/entity/player/Player;eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;",at = @At("HEAD"),cancellable = true)
    private void eat(Level pLevel, ItemStack pFood, CallbackInfoReturnable<ItemStack> cir){
        if(pLevel.dimension().location().toString().equals("minecraft:the_nether")) {
            cir.setReturnValue(pFood);
            cir.cancel();
        }
    }
}
