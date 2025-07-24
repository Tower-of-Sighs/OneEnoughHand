package com.mafuyu404.oneenoughhand.payload.s2c;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public class PlayerOffhandState {
    public record OffHandDisable(boolean value) implements CustomPacketPayload {
        public static final Type<OffHandDisable> TYPE = new Type<>(OneEnoughHand.ResourceLocationMod("off_hand_disable_payload"));
        public static final StreamCodec<FriendlyByteBuf, OffHandDisable> CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL,
                OffHandDisable::value,
                OffHandDisable::new
        );

        @Override
        public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public record OffHandLock(boolean value) implements CustomPacketPayload {
        public static final Type<OffHandLock> TYPE = new Type<>(OneEnoughHand.ResourceLocationMod("off_hand_lock_payload"));
        public static final StreamCodec<FriendlyByteBuf, OffHandLock> CODEC = StreamCodec.composite(
                ByteBufCodecs.BOOL,
                OffHandLock::value,
                OffHandLock::new
        );

        @Override
        public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
