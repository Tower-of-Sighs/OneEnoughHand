package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.api.IOffhandState;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OffhandStateProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IOffhandState> OFFHAND_STATE =
            CapabilityManager.get(new CapabilityToken<>() {});

    private IOffhandState state = null;
    private final LazyOptional<IOffhandState> opt = LazyOptional.of(this::createOffhandState);

    @Nonnull
    private IOffhandState createOffhandState() {
        if (state == null) {
            state = new OffhandState();
        }
        return state;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == OFFHAND_STATE) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createOffhandState();
        if (state instanceof OffhandState) {
            tag = ((OffhandState) state).serializeNBT();
        }
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createOffhandState();
        if (state instanceof OffhandState) {
            ((OffhandState) state).deserializeNBT(nbt);
        }
    }
}
