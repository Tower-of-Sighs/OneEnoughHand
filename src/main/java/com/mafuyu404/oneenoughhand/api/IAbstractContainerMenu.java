package com.mafuyu404.oneenoughhand.api;

import net.minecraft.server.level.ServerPlayer;

public interface IAbstractContainerMenu {
    ServerPlayer getPlayer();

    void setPlayer(ServerPlayer player);
}
