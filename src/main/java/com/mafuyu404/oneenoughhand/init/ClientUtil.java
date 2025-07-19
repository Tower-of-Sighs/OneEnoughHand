package com.mafuyu404.oneenoughhand.init;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtil {
    public static void updateOffhandState(String key, boolean value) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        player.getPersistentData().putBoolean(key, value);
    }
}
