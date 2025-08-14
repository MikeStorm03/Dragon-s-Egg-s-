package com.msg.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(FallingBlockEntity.class)
public class FallingBlockMixin {

    @Shadow private BlockState blockState;

    @Redirect(method = "tick()V",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private ItemEntity inject(FallingBlockEntity entity, ItemLike item) {

        ItemStack stack = new ItemStack(item);
        
        if (stack.is(Blocks.DRAGON_EGG.asItem())) {
            Map<String, String> props = new HashMap<>();
            for (Property<?> property : blockState.getProperties()) {
                Comparable<?> value = (Comparable<?>) blockState.getValue(property);
                props.put((property).getName(), value.toString());
            }
            stack.set(DataComponents.BLOCK_STATE, new BlockItemStateProperties(props));
        }

        return entity.spawnAtLocation(stack, 0);
    }
}