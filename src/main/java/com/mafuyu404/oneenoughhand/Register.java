package com.mafuyu404.oneenoughhand;

import com.mafuyu404.oneenoughhand.payload.s2c.PlayerOffhandState;
import com.mojang.serialization.Codec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Register {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, OneEnoughHand.MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> OFF_HAND_DISABLE = ATTACHMENT_TYPES.register(
            "off_hand_disable",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> OFF_HAND_LOCK = ATTACHMENT_TYPES.register(
            "off_hand_lock",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .copyOnDeath()
                    .build()
    );

    @EventBusSubscriber(modid = OneEnoughHand.MOD_ID)
    private static class Event {
        @SubscribeEvent
        public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new PlayerOffhandState.OffHandDisable(serverPlayer.getData(Register.OFF_HAND_DISABLE)));
                serverPlayer.connection.send(new PlayerOffhandState.OffHandLock(serverPlayer.getData(Register.OFF_HAND_LOCK)));
            }
        }

        @SubscribeEvent
        public static void clone(PlayerEvent.Clone event) {
            ServerPlayer original = (ServerPlayer) event.getOriginal();
            ServerPlayer entity = (ServerPlayer) event.getEntity();
            if (event.isWasDeath() && event.getOriginal().hasData(Register.OFF_HAND_DISABLE)) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> entity.connection.send(new PlayerOffhandState.OffHandDisable(original.getData(Register.OFF_HAND_DISABLE))), 1, TimeUnit.MILLISECONDS);
            }
            if (event.isWasDeath() && event.getOriginal().hasData(Register.OFF_HAND_LOCK)) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> entity.connection.send(new PlayerOffhandState.OffHandLock(original.getData(Register.OFF_HAND_LOCK))), 1, TimeUnit.MILLISECONDS);
            }
        }
    }
}
