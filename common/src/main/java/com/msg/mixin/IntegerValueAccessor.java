package com.msg.mixin;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.IntegerValue;;

@Mixin(IntegerValue.class)
public interface IntegerValueAccessor {
    @Invoker("create")
    static GameRules.Type<IntegerValue> create(int defaultValue, int min, int max, FeatureFlagSet requiredFeatures, BiConsumer<MinecraftServer, IntegerValue> changeListener) {
        throw new AssertionError("Dragon's Egg(S) failed at invoke integer value.");
    }
}
