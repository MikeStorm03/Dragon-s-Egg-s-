package com.msg.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@Mixin(Blocks.class)
public class MBlocksMixin {
    @ModifyArgs(method = "<clinit>",
                at = @At(value = "INVOKE",
                target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;"))
    private static void inject(Args args) {
        if (args.get(0) == "dragon_egg") {
            args.set(1, (Function<AbstractBlock.Settings, Block>) NewDragonEgg::new);
        }
    }
}
