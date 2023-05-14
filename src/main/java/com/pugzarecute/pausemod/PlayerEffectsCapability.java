package com.pugzarecute.pausemod;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class PlayerEffectsCapability {
    private List<MobEffectInstance> effects = new ArrayList();

    public List<MobEffectInstance> getEffects() {
        return effects;
    }

    public void addToList(Player p){
        effects = p.getActiveEffects().stream().toList();
    }
    public void clean(){
        effects = new ArrayList<>();
    }
    public void copyFrom(PlayerEffectsCapability p){
        this.effects = p.effects;
    }

    public CompoundTag saveNBT(){
        CompoundTag f = new CompoundTag();
        int i = 0;
        for (MobEffectInstance m : effects){
            CompoundTag x = new CompoundTag();
            x.putString("effect",m.getDescriptionId());
            x.putInt("amp",m.getAmplifier());
            x.putInt("duration",m.getDuration());
            f.put(String.valueOf(++i),x);
        }
        CompoundTag t = new CompoundTag();
        t.put("effects_list",f);
        return t;
    }

    public void loadNBT(CompoundTag nbt) {
        CompoundTag effedcts = nbt.getCompound("effects_list");
        effedcts.getAllKeys().forEach((s) ->{
            CompoundTag c = effedcts.getCompound(s);
            effects.add(new MobEffectInstance(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(c.getString("effect"))),c.getInt("duration"),c.getInt("amp")));
        });
    }

    public void addToPlayer(Player entity) {
        effects.forEach(entity::addEffect);
    }
}
