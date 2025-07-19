package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.data.OffhandAttachments;
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
            if ("OffhandDisable".equals(key)) {
                serverPlayer.setAttached(OffhandAttachments.OFFHAND_DISABLED, value);
            } else if ("OffhandLock".equals(key)) {
                serverPlayer.setAttached(OffhandAttachments.OFFHAND_LOCKED, value);
            }
        }
    }

    public static boolean isOffhandDisabled(Player player) {
        if (player == null) return false;

        if (player instanceof ServerPlayer serverPlayer) {
            Boolean result = serverPlayer.getAttached(OffhandAttachments.OFFHAND_DISABLED);
            return result != null ? result : false;
        } else {
            Boolean result = player.getAttached(OffhandAttachments.OFFHAND_DISABLED);
            return result != null ? result : false;
        }
    }

    public static boolean isOffhandLocked(Player player) {
        if (player == null) return false;

        if (player instanceof ServerPlayer serverPlayer) {
            Boolean result = serverPlayer.getAttached(OffhandAttachments.OFFHAND_LOCKED);
            return result != null ? result : false;
        } else {
            Boolean result = player.getAttached(OffhandAttachments.OFFHAND_LOCKED);
            return result != null ? result : false;
        }
    }
}