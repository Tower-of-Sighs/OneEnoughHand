package com.mafuyu404.oneenoughhand.data;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;

public class OffhandAttachments {
    public static final AttachmentType<Boolean> OFFHAND_DISABLED = AttachmentRegistry.<Boolean>builder()
            .persistent(Codec.BOOL)
            .initializer(() -> false)
            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.targetOnly())
            .buildAndRegister(ResourceLocation.fromNamespaceAndPath(OneEnoughHand.MODID, "offhand_disabled"));

    public static final AttachmentType<Boolean> OFFHAND_LOCKED = AttachmentRegistry.<Boolean>builder()
            .persistent(Codec.BOOL)
            .initializer(() -> false)
            .syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.targetOnly())
            .buildAndRegister(ResourceLocation.fromNamespaceAndPath(OneEnoughHand.MODID, "offhand_locked"));

    public static void register() {
    }
}