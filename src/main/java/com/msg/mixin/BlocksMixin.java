package com.msg.mixin;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Overwrite
    private static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        if (id == "dragon_egg") {
            factory = NewDragonEgg::new;
        }
        return Blocks.register((RegistryKey<Block>)RegistryKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id)), factory, settings);
    }
}
