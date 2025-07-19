package com.mafuyu404.oneenoughhand.mixin;

import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = KeyMapping.class)
public class KeyMappingMixin {
    @Shadow @Final private String name;

    @Inject(method = "consumeClick", at = @At("HEAD"), cancellable = true)
    private void qqq(CallbackInfoReturnable<Boolean> cir) {
        if (this.name.equals("key.swapOffhand")) {
            cir.setReturnValue(false);
        }
    }
}
