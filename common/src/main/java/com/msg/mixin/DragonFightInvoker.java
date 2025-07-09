package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.dimension.end.EndDragonFight;

@Mixin(EndDragonFight.class)
public interface DragonFightInvoker {
    @Invoker("spawnExitPortal")
    public void invokegenerateEndPortal(boolean previouslyKilled);
}
