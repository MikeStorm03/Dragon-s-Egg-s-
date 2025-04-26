package com.msg.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

@Mixin(Blocks.class)
public class MBlocksMixin {

    @Inject(method = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;",
            at = @At("HEAD"),
            cancellable = true)
    private static void inject(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, CallbackInfoReturnable<Block> cir){
        if (id == "dragon_egg") {
            cir.setReturnValue(Blocks.register((RegistryKey<Block>)RegistryKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id)), NewDragonEgg::new, settings));
        }
    }
}
