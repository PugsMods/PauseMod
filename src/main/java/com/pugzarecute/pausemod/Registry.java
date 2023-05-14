package com.pugzarecute.pausemod;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Registry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Pausemod.MODID);

    public static final RegistryObject<MobEffect> TIME_STOPPED = EFFECTS.register("time_dilation", () -> new MobEffect(MobEffectCategory.NEUTRAL,0xfc03be));
}
