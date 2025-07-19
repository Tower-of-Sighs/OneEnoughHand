package com.mafuyu404.oneenoughhand.network;

import com.mafuyu404.oneenoughhand.init.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerOffhandStatePacket {
    private final String key;
    private final boolean value;

    public PlayerOffhandStatePacket(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    public static void encode(PlayerOffhandStatePacket msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.key);
        buffer.writeBoolean(msg.value);
    }

    public static PlayerOffhandStatePacket decode(FriendlyByteBuf buffer) {
        return new PlayerOffhandStatePacket(buffer.readUtf(), buffer.readBoolean());
    }

    public static void handle(PlayerOffhandStatePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientUtil.updateOffhandState(msg.key, msg.value);
        });
        ctx.get().setPacketHandled(true);
    }
}
