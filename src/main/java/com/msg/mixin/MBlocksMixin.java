package com.msg.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@Mixin(Blocks.class)
public class MBlocksMixin {

    // Getting the block id using @Inject yet somehow it worked perfectly
    private static String block_id;
    @Inject(method = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;",
            at = @At("HEAD"),
            cancellable = false)
    private static void getBlockID(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, CallbackInfoReturnable ci){
        block_id = id;
    }

    // Replace with the old dragon egg
    @ModifyVariable(method = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;",
                    at = @At("HEAD"))
    private static Function<AbstractBlock.Settings, Block> injected(Function<AbstractBlock.Settings, Block> factory) {
        return (block_id == "dragon_egg") ? NewDragonEgg::new : factory; 
    }
}
