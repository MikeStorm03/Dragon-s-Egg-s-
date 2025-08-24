package com.msg.dragons_eggs.server;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.msg.dragons_eggs.DragonsEggSConstants;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class DragonEggSaveAndLoader extends SavedData {

    public Integer currentEggNumber = 0;

    public DragonEggSaveAndLoader(Integer currentEggNumber) {
        this.currentEggNumber = currentEggNumber;
    }

    public static final Codec<DragonEggSaveAndLoader> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.INT.fieldOf("currentEggNumber").forGetter(state -> state.currentEggNumber)
        ).apply(instance, DragonEggSaveAndLoader::new)
    );

    public static DragonEggSaveAndLoader createNew() {
        DragonEggSaveAndLoader state = new DragonEggSaveAndLoader(0);
        return state;
    }

    private static final SavedDataType<DragonEggSaveAndLoader> type = new SavedDataType<>(
        DragonsEggSConstants.ID,
        DragonEggSaveAndLoader::createNew,
        CODEC,
        null
    );
 
    public static DragonEggSaveAndLoader getServerState(MinecraftServer server) {
        DragonEggSaveAndLoader state = server.getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(type);
        state.setDirty();
        return state;
    }
}