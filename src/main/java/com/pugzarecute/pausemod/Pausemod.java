package com.pugzarecute.pausemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Pausemod.MODID)
@Mod.EventBusSubscriber(modid = Pausemod.MODID )
public class Pausemod {
    public Pausemod(){
        Registry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final String MODID = "pausemod";
    @SubscribeEvent
    public static void onAttachCap(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerEffectsCapabilityProvider.EFFECTS).isPresent()) event.addCapability(new ResourceLocation(Pausemod.MODID,"effects"),new PlayerEffectsCapabilityProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getOriginal().getCapability(PlayerEffectsCapabilityProvider.EFFECTS).ifPresent(old ->{
                event.getOriginal().getCapability(PlayerEffectsCapabilityProvider.EFFECTS).ifPresent( newe ->{
                    newe.copyFrom(old);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onCapReg(RegisterCapabilitiesEvent event){
        event.register(PlayerEffectsCapability.class);
    }

    @SubscribeEvent
    public static void onDimChange(PlayerEvent.PlayerChangedDimensionEvent event){
        if(event.getTo().location().toString().equals("minecraft:the_nether")){
            event.getEntity().getCapability(PlayerEffectsCapabilityProvider.EFFECTS).ifPresent(playerEffectsCapability -> {
                    playerEffectsCapability.addToList(event.getEntity());
                    event.getEntity().removeAllEffects();
            });
        } else if(event.getFrom().location().toString().equals("minecraft:the_nether")){
            event.getEntity().getCapability(PlayerEffectsCapabilityProvider.EFFECTS).ifPresent(playerEffectsCapability -> {
                event.getEntity().removeAllEffects();
                playerEffectsCapability.addToPlayer(event.getEntity());
            });
        }
    }
    public static void playerTickHook(Player p){
            if(!p.hasEffect(Registry.TIME_STOPPED.get())){
                p.addEffect(new MobEffectInstance(Registry.TIME_STOPPED.get(),Integer.MAX_VALUE));
            }
    }

}
