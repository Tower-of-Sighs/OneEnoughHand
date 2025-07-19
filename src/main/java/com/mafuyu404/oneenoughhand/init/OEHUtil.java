package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.network.NetworkHandler;
import com.mafuyu404.oneenoughhand.network.PlayerOffhandStatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class OEHUtil {
    public static final String DISABLE_KEY = "OffhandDisable";
    public static final String LOCK_KEY = "OffhandLock";

    public static void updateOffhandState(Player player, String key, boolean value) {
        player.getCapability(OffhandStateProvider.OFFHAND_STATE).ifPresent(iOffhandState -> {
            if (key.equals(DISABLE_KEY)) iOffhandState.setDisabled(value);
            if (key.equals(LOCK_KEY)) iOffhandState.setLocked(value);
        });
//        player.getPersistentData().putBoolean(key, value);
        if (!player.isLocalPlayer()) syncOffhandState((ServerPlayer) player);
    }
    public static boolean getOffhandState(Player player, String key) {
        if (player == null) return false;
//        return player.getPersistentData().getBoolean(key);
        AtomicBoolean result = new AtomicBoolean(false);
        player.getCapability(OffhandStateProvider.OFFHAND_STATE).ifPresent(iOffhandState -> {
            if (key.equals(DISABLE_KEY)) result.set(iOffhandState.isDisabled());
            if (key.equals(LOCK_KEY)) result.set(iOffhandState.isLocked());
        });
        return result.get();
    }

    public static void disableOffhand(Player player) {
        updateOffhandState(player, DISABLE_KEY, true);
    }
    public static void enableOffhand(Player player) {
        updateOffhandState(player, DISABLE_KEY, false);
    }
    public static void lockOffhand(Player player) {
        updateOffhandState(player, LOCK_KEY, true);
    }
    public static void unlockOffhand(Player player) {
        updateOffhandState(player, LOCK_KEY, false);
    }

    public static boolean isOffhandDisabled(Player player) {
        if (player == null) return false;
        return getOffhandState(player, DISABLE_KEY);
    }
    public static boolean isOffhandLocked(Player player) {
        if (player == null) return false;
        return getOffhandState(player, LOCK_KEY);
    }

    public static void syncOffhandState(ServerPlayer player) {
        NetworkHandler.sendToClient(player, new PlayerOffhandStatePacket(OEHUtil.DISABLE_KEY, OEHUtil.isOffhandDisabled(player)));
        NetworkHandler.sendToClient(player, new PlayerOffhandStatePacket(OEHUtil.LOCK_KEY, OEHUtil.isOffhandLocked(player)));
    }
}
