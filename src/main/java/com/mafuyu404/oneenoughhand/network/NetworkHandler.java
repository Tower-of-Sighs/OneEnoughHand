package com.mafuyu404.oneenoughhand.network;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(OneEnoughHand.MODID, "sync_data"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    public static void register() {
        int packetId = 0;
        CHANNEL.registerMessage(packetId++, PlayerOffhandStatePacket.class, PlayerOffhandStatePacket::encode, PlayerOffhandStatePacket::decode, PlayerOffhandStatePacket::handle);

    }

    public static void sendToClient(ServerPlayer player, Object packet) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
}