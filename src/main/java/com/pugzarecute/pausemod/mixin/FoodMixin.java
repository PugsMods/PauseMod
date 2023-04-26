package com.pugzarecute.pausemod.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodMixin {
    @Inject(method = "Lnet/minecraft/world/food/FoodData;tick(Lnet/minecraft/world/entity/player/Player;)V", at = @At("HEAD"),cancellable = true)
    private void playerTick(Player pPlayer, CallbackInfo ci){
        if(pPlayer.level.dimension().toString().equals("minecraft:the_nether")){
            ci.cancel();
        }
    }

}
