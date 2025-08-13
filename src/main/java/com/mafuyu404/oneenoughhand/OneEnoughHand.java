package com.mafuyu404.oneenoughhand;

import com.mafuyu404.oneenoughhand.init.OffhandCommands;
import net.fabricmc.api.ModInitializer;

public class OneEnoughHand implements ModInitializer {
    public static final String MODID = "oneenoughhand";

    @Override
    public void onInitialize() {
        OffhandCommands.register();

    }
}