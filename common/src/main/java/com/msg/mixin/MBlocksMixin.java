package com.msg.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.msg.block.NewDragonEgg;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(Blocks.class)
public class MBlocksMixin {
    @ModifyArgs(method = "<clinit>",
                at = @At(value = "INVOKE",
                target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;"))
    private static void inject(Args args) {
        if (args.get(0) == "dragon_egg") {
            args.set(1, (Function<BlockBehaviour.Properties, Block>) NewDragonEgg::new);
        }
    }
}