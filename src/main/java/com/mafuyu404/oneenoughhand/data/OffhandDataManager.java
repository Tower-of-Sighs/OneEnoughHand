package com.mafuyu404.oneenoughhand.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OffhandDataManager extends SavedData {
    private static final String DATA_NAME = "oneenoughhand_data";
    private final Map<UUID, CompoundTag> playerData = new HashMap<>();

    public OffhandDataManager() {
        super();
    }

    public OffhandDataManager(CompoundTag tag) {
        this();
        CompoundTag playersTag = tag.getCompound("players");
        for (String uuidString : playersTag.getAllKeys()) {
            UUID uuid = UUID.fromString(uuidString);
            playerData.put(uuid, playersTag.getCompound(uuidString));
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        CompoundTag playersTag = new CompoundTag();
        for (Map.Entry<UUID, CompoundTag> entry : playerData.entrySet()) {
            playersTag.put(entry.getKey().toString(), entry.getValue());
        }
        tag.put("players", playersTag);
        return tag;
    }

    public static OffhandDataManager get(MinecraftServer server) {
        DimensionDataStorage storage = server.overworld().getDataStorage();
        return storage.computeIfAbsent(OffhandDataManager::new, OffhandDataManager::new, DATA_NAME);
    }

    public void setOffhandState(UUID playerUUID, String key, boolean value) {
        CompoundTag playerTag = playerData.computeIfAbsent(playerUUID, k -> new CompoundTag());
        playerTag.putBoolean(key, value);
        setDirty();
    }

    public boolean getOffhandState(UUID playerUUID, String key) {
        CompoundTag playerTag = playerData.get(playerUUID);
        return playerTag != null && playerTag.getBoolean(key);
    }

    public void clearPlayerData(UUID playerUUID) {
        playerData.remove(playerUUID);
        setDirty();
    }
}