package com.msg.dragons_eggs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.msg.dragons_eggs.Constants;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Blocks;

@Mixin(Items.class)
public class ItemsMixin {
    @ModifyArgs(method = "<clinit>",
                at = @At(value = "INVOKE",
                target = "Lnet/minecraft/world/item/Items;registerBlock(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;"))
    private static void ModArgs(Args args) {
        if ((BuiltInRegistries.BLOCK.getKey(args.get(0)).equals(BuiltInRegistries.BLOCK.getKey(Blocks.DRAGON_EGG)))){
            args.set(1, (new Item.Properties()).component(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(Constants.GENERATION, 0)).rarity(Rarity.EPIC));
        }
    }
}
