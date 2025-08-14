package com.msg.server;

import com.msg.DragonsEggSConstants;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

public class DragonEggSaveAndLoader extends SavedData {
    public Integer currentEggNumber = 0;
    
    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.putInt("currentEggNumber", currentEggNumber);
        return nbt;
    }

    public static DragonEggSaveAndLoader createFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        DragonEggSaveAndLoader state = new DragonEggSaveAndLoader();
        state.currentEggNumber = tag.getInt("currentEggNumber");
        return state;
    }
 
    public static DragonEggSaveAndLoader createNew() {
        DragonEggSaveAndLoader state = new DragonEggSaveAndLoader();
        state.currentEggNumber = 0;
        return state;
    }

    private static final Factory<DragonEggSaveAndLoader> type = new Factory<>(
        DragonEggSaveAndLoader::createNew,
        DragonEggSaveAndLoader::createFromNbt,
        null
    );
 
    public static DragonEggSaveAndLoader getServerState(MinecraftServer server) {
        ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
        assert serverWorld != null;
        DragonEggSaveAndLoader state = serverWorld.getDataStorage().computeIfAbsent(type, DragonsEggSConstants.ID);

        state.setDirty();
 
        return state;
    }
}