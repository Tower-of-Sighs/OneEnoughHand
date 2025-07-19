package com.mafuyu404.oneenoughhand.event;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import com.mafuyu404.oneenoughhand.init.OEHUtil;
import com.mafuyu404.oneenoughhand.network.NetworkHandler;
import com.mafuyu404.oneenoughhand.network.PlayerOffhandStatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OneEnoughHand.MODID)
public class LoadOffhandStateEvent {
    @SubscribeEvent
    public static void load(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.isLocalPlayer()) return;
            OEHUtil.syncOffhandState((ServerPlayer) player);
        }
    }

    @SubscribeEvent
    public static void restore(PlayerEvent.Clone event) {
        OEHUtil.updateOffhandState(event.getEntity(), OEHUtil.DISABLE_KEY, OEHUtil.isOffhandDisabled(event.getOriginal()));
        OEHUtil.updateOffhandState(event.getEntity(), OEHUtil.LOCK_KEY, OEHUtil.isOffhandLocked(event.getOriginal()));
    }
}
