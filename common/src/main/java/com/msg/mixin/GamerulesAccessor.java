package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;

@Mixin(GameRules.class)
public interface GamerulesAccessor {
    @Invoker("register")
    public static <T extends Value<T>> Key<T> register(String name, Category category, Type<T> type){
        throw new AssertionError("Dragon's Egg(S) register Gamerules failed!");
    }
}
