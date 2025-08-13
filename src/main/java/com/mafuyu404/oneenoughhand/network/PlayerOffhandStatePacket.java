package com.mafuyu404.oneenoughhand.network;

import com.mafuyu404.oelib.api.net.INetworkContext;
import com.mafuyu404.oelib.api.net.NetworkPacket;
import com.mafuyu404.oelib.api.net.Side;
import com.mafuyu404.oelib.api.net.SimplePacket;
import com.mafuyu404.oneenoughhand.init.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;

@NetworkPacket(side = Side.CLIENT)
public class PlayerOffhandStatePacket extends SimplePacket<PlayerOffhandStatePacket> {

    private final String key;
    private final Boolean value;

    public PlayerOffhandStatePacket(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(key);
        buf.writeBoolean(value);
    }

    public static PlayerOffhandStatePacket decode(FriendlyByteBuf buf) {
        return new PlayerOffhandStatePacket(buf.readUtf(), buf.readBoolean());
    }

    @Override
    public void handleClient(INetworkContext context) {
        ClientUtil.updateOffhandState(key, value);
    }
}
