package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.msg.util.Consants;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

@Mixin(Items.class)
public class ItemsMixin {
    @Inject(method = "Lnet/minecraft/item/Items;register(Lnet/minecraft/item/BlockItem;)Lnet/minecraft/item/Item;", at = @At("TAIL"), cancellable = false)
    private static void inject(BlockItem item, CallbackInfoReturnable ci){
        if (item.getBlock().equals(Blocks.DRAGON_EGG)){
            item = new BlockItem(Blocks.DRAGON_EGG, (new Item.Settings()).component(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT.with(Consants.GENERATION, 0)).rarity(Rarity.EPIC));
        }
    }
}
