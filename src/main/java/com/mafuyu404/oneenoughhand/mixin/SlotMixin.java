package com.mafuyu404.oneenoughhand.mixin;

import com.mafuyu404.oneenoughhand.init.OEHUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Slot.class)
public abstract class SlotMixin {
    @Shadow public int index;

    @Inject(method = "isActive", at = @At("HEAD"), cancellable = true)
    private void qqq(CallbackInfoReturnable<Boolean> cir) {
        if (!OEHUtil.isOffhandDisabled(Minecraft.getInstance().player)) return;
        if (Minecraft.getInstance().screen instanceof InventoryScreen) {
            if (this.index == 45) {
                cir.setReturnValue(false);
            }
        }
    }
}