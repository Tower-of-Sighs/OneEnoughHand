package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.network.NetworkHandler;
import com.mafuyu404.oneenoughhand.network.PlayerOffhandStatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class OEHUtil {
    public static void disableOffhand(Player player) {
        updateOffhandState(player, "OffhandDisable", true);
    }
    public static void enableOffhand(Player player) {
        updateOffhandState(player, "OffhandDisable", false);
    }
    public static void lockOffhand(Player player) {
        updateOffhandState(player, "OffhandLock", true);
    }
    public static void unlockOffhand(Player player) {
        updateOffhandState(player, "OffhandLock", false);
    }
    public static void updateOffhandState(Player player, String key, boolean value) {
        player.getPersistentData().putBoolean(key, value);
        if (!player.isLocalPlayer()) {
            NetworkHandler.sendToClient((ServerPlayer) player, new PlayerOffhandStatePacket(key, value));
        }
    }
    public static boolean isOffhandDisabled(Player player) {
        if (player == null) return false;
        return player.getPersistentData().getBoolean("OffhandDisable");
    }
    public static boolean isOffhandLocked(Player player) {
        if (player == null) return false;
        return player.getPersistentData().getBoolean("OffhandLock");
    }
}
