package com.pugzarecute.pausemod.mixin;

import com.pugzarecute.pausemod.Pausemod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
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
    @Redirect(at = @At(value="INVOKE",target = "Lnet/minecraftforge/event/ForgeEventFactory;onPlayerPreTick(Lnet/minecraft/world/entity/player/Player;)V"),method = "Lnet/minecraft/world/entity/player/Player;tick()V")
    private void tickHookPre(Player player){
        if(player.level.dimension().location().toString().equals("minecraft:the_nether")){
            Pausemod.playerTickHook(player);
        }else{
            ForgeEventFactory.onPlayerPreTick(player);
        }
    }

    @Redirect(at = @At(value="INVOKE",target = "Lnet/minecraftforge/event/ForgeEventFactory;onPlayerPostTick(Lnet/minecraft/world/entity/player/Player;)V"),method = "Lnet/minecraft/world/entity/player/Player;tick()V")
    private void tickHookPost(Player player){
        if(!player.level.dimension().location().toString().equals("minecraft:the_nether")){
            ForgeEventFactory.onPlayerPostTick(player);
        }
    }

}
