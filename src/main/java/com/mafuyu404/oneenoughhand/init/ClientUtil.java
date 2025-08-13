package com.mafuyu404.oneenoughhand.init;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class ClientUtil {
    private static final Map<String, Boolean> clientOffhandStates = new HashMap<>();

    public static void updateOffhandState(String key, boolean value) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        String playerKey = player.getUUID() + "_" + key;
        clientOffhandStates.put(playerKey, value);
    }
    public static boolean getOffhandState(String key) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return false;

        String playerKey = player.getUUID() + "_" + key;
        return clientOffhandStates.getOrDefault(playerKey, false);
    }
}