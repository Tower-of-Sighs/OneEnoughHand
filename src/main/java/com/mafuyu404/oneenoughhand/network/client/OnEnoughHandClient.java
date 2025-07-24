package com.mafuyu404.oneenoughhand.network.client;

import com.mafuyu404.oneenoughhand.OneEnoughHand;
import com.mafuyu404.oneenoughhand.Register;
import com.mafuyu404.oneenoughhand.payload.s2c.PlayerOffhandState;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = OneEnoughHand.MOD_ID)
public class OnEnoughHandClient {
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(OneEnoughHand.MOD_ID);
        registrar.playBidirectional(
                PlayerOffhandState.OffHandDisable.TYPE,
                PlayerOffhandState.OffHandDisable.CODEC,
                new DirectionalPayloadHandler<>(
                        (payload, context) -> {
                            if (minecraft.player != null) {
                                minecraft.player.setData(Register.OFF_HAND_DISABLE, payload.value());
                            }
                        },
                        (payload, context) -> {
                        }
                )
        );

        registrar.playBidirectional(
                PlayerOffhandState.OffHandLock.TYPE,
                PlayerOffhandState.OffHandLock.CODEC,
                new DirectionalPayloadHandler<>(
                        (payload, context) -> {
                            if (minecraft.player != null) {
                                minecraft.player.setData(Register.OFF_HAND_LOCK, payload.value());
                            }
                        },
                        (payload, context) -> {
                        }
                )
        );
    }
}
