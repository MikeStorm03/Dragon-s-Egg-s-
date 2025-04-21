package com.msg.util;

import com.msg.DragonsEggS;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

public class DragonEggSaveAndLoader extends PersistentState {
    public Integer currentEggNumber = 0;
    
    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putInt("currentEggNumber", currentEggNumber);
        return nbt;
    }

    public static DragonEggSaveAndLoader createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        DragonEggSaveAndLoader state = new DragonEggSaveAndLoader();
        state.currentEggNumber = tag.getInt("currentEggNumber");
        return state;
    }
 
    public static DragonEggSaveAndLoader createNew() {
        DragonEggSaveAndLoader state = new DragonEggSaveAndLoader();
        state.currentEggNumber = 0;
        return state;
    }

    private static final Type<DragonEggSaveAndLoader> type = new Type<>(
        DragonEggSaveAndLoader::createNew,
        DragonEggSaveAndLoader::createFromNbt,
        null
    );
 
    public static DragonEggSaveAndLoader getServerState(MinecraftServer server) {
        ServerWorld serverWorld = server.getWorld(World.OVERWORLD);
        assert serverWorld != null;
        DragonEggSaveAndLoader state = serverWorld.getPersistentStateManager().getOrCreate(type, DragonsEggS.MOD_ID);

        state.markDirty();
 
        return state;
    }
}
