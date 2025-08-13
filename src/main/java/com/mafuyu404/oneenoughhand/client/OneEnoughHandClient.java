package com.mafuyu404.oneenoughhand.client;

import com.mafuyu404.oelib.api.net.NetworkManager;
import com.mafuyu404.oneenoughhand.network.PlayerOffhandStatePacket;
import net.fabricmc.api.ClientModInitializer;

public class OneEnoughHandClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NetworkManager.registerPackets(PlayerOffhandStatePacket.class);
    }
}
