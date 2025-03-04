package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.boss.dragon.EnderDragonFight;

@Mixin(EnderDragonFight.class)
public interface DragonFightInvoker {
    @Invoker("generateEndPortal")
    public void invokegenerateEndPortal(boolean previouslyKilled);
}