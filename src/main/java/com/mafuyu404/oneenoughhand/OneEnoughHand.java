package com.mafuyu404.oneenoughhand;

import com.mafuyu404.oneenoughhand.init.OffhandCommands;
import com.mafuyu404.oneenoughhand.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;

public class OneEnoughHand implements ModInitializer {
    public static final String MODID = "oneenoughhand";

    @Override
    public void onInitialize() {
        OffhandCommands.register();

        NetworkHandler.register();
    }
}