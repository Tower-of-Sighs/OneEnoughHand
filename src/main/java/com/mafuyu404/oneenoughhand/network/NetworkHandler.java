package com.mafuyu404.oneenoughhand.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class NetworkHandler {

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(
                PlayerOffhandStatePacket.ID,
                (client, handler, buf, responseSender) -> {
                    PlayerOffhandStatePacket packet = PlayerOffhandStatePacket.read(buf);
                    client.execute(() -> PlayerOffhandStatePacket.handle(packet));
                }
        );
    }

    public static void sendToClient(ServerPlayer player, PlayerOffhandStatePacket packet) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.write(buf);
        ServerPlayNetworking.send(player, PlayerOffhandStatePacket.ID, buf);
    }
}