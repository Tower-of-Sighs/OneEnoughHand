package com.mafuyu404.oneenoughhand;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Utils {
    public static void give(Player player, ItemStack itemStack) {
        boolean flag = player.getInventory().add(itemStack);
        if (flag && itemStack.isEmpty()) {
            itemStack.setCount(1);
            ItemEntity itementity1 = player.drop(itemStack, false);
            if (itementity1 != null) {
                itementity1.makeFakeItem();
            }

//            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.containerMenu.broadcastChanges();
        } else {
            ItemEntity itementity = player.drop(itemStack, false);
            if (itementity != null) {
                itementity.setNoPickUpDelay();
                itementity.setTarget(player.getUUID());
            }
        }
    }
}
