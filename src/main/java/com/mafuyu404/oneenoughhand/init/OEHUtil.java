package com.mafuyu404.oneenoughhand.init;

import com.mafuyu404.oneenoughhand.Register;
import com.mafuyu404.oneenoughhand.payload.s2c.PlayerOffhandState;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class OEHUtil {
    public static void disableOffhand(ServerPlayer player) {
        player.setData(Register.OFF_HAND_DISABLE, true);
        player.connection.send(new PlayerOffhandState.OffHandDisable(true));
    }

    public static void enableOffhand(ServerPlayer player) {
        player.setData(Register.OFF_HAND_DISABLE, false);
        player.connection.send(new PlayerOffhandState.OffHandDisable(false));
    }

    public static void lockOffhand(ServerPlayer player) {
        player.setData(Register.OFF_HAND_LOCK, true);
        player.connection.send(new PlayerOffhandState.OffHandLock(true));
    }

    public static void unlockOffhand(ServerPlayer player) {
        player.setData(Register.OFF_HAND_LOCK, false);
        player.connection.send(new PlayerOffhandState.OffHandLock(false));
    }

    public static boolean isOffhandDisabled(Player player) {
        if (player == null) return false;
        return player.hasData(Register.OFF_HAND_DISABLE) && player.getData(Register.OFF_HAND_DISABLE);
    }

    public static boolean isOffhandLocked(Player player) {
        if (player == null) return false;
        return player.hasData(Register.OFF_HAND_LOCK) && player.getData(Register.OFF_HAND_LOCK);
    }
}