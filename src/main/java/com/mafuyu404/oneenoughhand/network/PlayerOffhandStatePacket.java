package com.mafuyu404.oneenoughhand.network;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import com.mafuyu404.oneenoughhand.init.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class PlayerOffhandStatePacket {
    public static final ResourceLocation ID = new ResourceLocation(OneEnoughHand.MODID, "player_offhand");

    private final String key;
    private final Boolean value;

    public PlayerOffhandStatePacket(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(key);
        buf.writeBoolean(value);
    }

    public static PlayerOffhandStatePacket read(FriendlyByteBuf buf) {
        return new PlayerOffhandStatePacket(buf.readUtf(), buf.readBoolean());
    }

    public static void handle(PlayerOffhandStatePacket packet) {
        ClientUtil.updateOffhandState(packet.key, packet.value);
    }

    public String getKey() {
        return key;
    }

    public boolean getValue() {
        return value;
    }
}
