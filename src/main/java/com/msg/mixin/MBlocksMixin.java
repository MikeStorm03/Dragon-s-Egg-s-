package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

@Mixin(Blocks.class)
public class MBlocksMixin {

    @Inject(method = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;",
            at = @At("RETURN"),
            cancellable = true)
    private static void injected(String id, Block block, CallbackInfoReturnable<Block> cir) {
        if (id == "dragon_egg") {
            cir.setReturnValue((Block)Registry.register(Registries.BLOCK, id, new NewDragonEgg(Settings.create().mapColor(MapColor.BLACK).strength(3.0F, 9.0F).luminance((state) -> {
                return 1;
            }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))));
        }
    }
}
