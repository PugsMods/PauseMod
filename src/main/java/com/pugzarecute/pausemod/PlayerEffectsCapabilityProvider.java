package com.pugzarecute.pausemod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEffectsCapabilityProvider  implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerEffectsCapability> EFFECTS = CapabilityManager.get(new CapabilityToken<PlayerEffectsCapability>() {});

    private PlayerEffectsCapability effects = null;
    private final LazyOptional<PlayerEffectsCapability> lazyOptional = LazyOptional.of(this::create);

    private PlayerEffectsCapability create() {
        if(this.effects == null) effects = new PlayerEffectsCapability();
        return this.effects;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == EFFECTS) return  lazyOptional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return create().saveNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        create().loadNBT(nbt);
    }
}
