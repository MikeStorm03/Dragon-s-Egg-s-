package com.msg.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.msg.DragonsEggS;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;
import net.minecraft.world.World;

public class DragonEggSaveAndLoader extends PersistentState {
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

    private static final PersistentStateType<DragonEggSaveAndLoader> type = new PersistentStateType<>(
        DragonsEggS.MOD_ID,
        DragonEggSaveAndLoader::createNew,
        CODEC,
        null
    );
 
    public static DragonEggSaveAndLoader getServerState(MinecraftServer server) {
        DragonEggSaveAndLoader state = server.getWorld(World.OVERWORLD).getPersistentStateManager().getOrCreate(type);
        state.markDirty();
        return state;
    }
}
