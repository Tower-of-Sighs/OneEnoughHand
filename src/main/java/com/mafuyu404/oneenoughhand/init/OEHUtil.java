package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.data.OffhandDataManager;
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
        if (player instanceof ServerPlayer serverPlayer) {
            OffhandDataManager dataManager = OffhandDataManager.get(serverPlayer.getServer());
            dataManager.setOffhandState(player.getUUID(), key, value);

            NetworkHandler.sendToClient(serverPlayer, new PlayerOffhandStatePacket(key, value));
        }
    }

    public static boolean isOffhandDisabled(Player player) {
        if (player == null) return false;

        if (player.isLocalPlayer()) {
            return ClientUtil.getOffhandState("OffhandDisable");
        } else if (player instanceof ServerPlayer serverPlayer) {
            OffhandDataManager dataManager = OffhandDataManager.get(serverPlayer.getServer());
            return dataManager.getOffhandState(player.getUUID(), "OffhandDisable");
        }
        return false;
    }

    public static boolean isOffhandLocked(Player player) {
        if (player == null) return false;

        if (player.isLocalPlayer()) {
            return ClientUtil.getOffhandState("OffhandLock");
        } else if (player instanceof ServerPlayer serverPlayer) {
            OffhandDataManager dataManager = OffhandDataManager.get(serverPlayer.getServer());
            return dataManager.getOffhandState(player.getUUID(), "OffhandLock");
        }
        return false;
    }
}