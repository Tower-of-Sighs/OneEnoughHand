package com.mafuyu404.oneenoughhand.init;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Utils {
    public static void disableOffhand(Player player) {
        player.getPersistentData().putBoolean("OffhandDisable", true);
    }
    public static void enableOffhand(Player player) {
        player.getPersistentData().putBoolean("OffhandDisable", false);
    }
    public static void lockOffhand(Player player) {
        player.getPersistentData().putBoolean("OffhandLock", true);
    }
    public static void unlockOffhand(Player player) {
        player.getPersistentData().putBoolean("OffhandLock", false);
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
