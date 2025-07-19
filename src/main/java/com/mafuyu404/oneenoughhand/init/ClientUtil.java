package com.mafuyu404.oneenoughhand.init;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import static com.mafuyu404.oneenoughhand.init.OEHUtil.DISABLE_KEY;
import static com.mafuyu404.oneenoughhand.init.OEHUtil.LOCK_KEY;

public class ClientUtil {
    public static void updateOffhandState(String key, boolean value) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        OEHUtil.updateOffhandState(player, key, value);
    }
}
