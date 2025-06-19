package com.msg.mixin;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.BooleanValue;

@Mixin(BooleanValue.class)
public interface BooleanValueAccessor {
    @Invoker("create")
    static GameRules.Type<BooleanValue> create(boolean defaultValue, BiConsumer<MinecraftServer, BooleanValue> changeListener){
        throw new AssertionError("Dragon's Egg(S) failed at invoke integer value.");
    }
}
