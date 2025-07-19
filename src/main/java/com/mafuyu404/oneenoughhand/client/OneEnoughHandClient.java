package com.mafuyu404.oneenoughhand.client;

import com.mafuyu404.oneenoughhand.network.NetworkHandler;
import net.fabricmc.api.ClientModInitializer;

public class OneEnoughHandClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NetworkHandler.register();
    }
}
