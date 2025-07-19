package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.api.IOffhandState;
import net.minecraft.nbt.CompoundTag;

public class OffhandState implements IOffhandState {
    private boolean disabled = false;
    private boolean locked = false;

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(OEHUtil.DISABLE_KEY, disabled);
        tag.putBoolean(OEHUtil.LOCK_KEY, locked);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        disabled = tag.getBoolean(OEHUtil.DISABLE_KEY);
        locked = tag.getBoolean(OEHUtil.LOCK_KEY);
    }
}
