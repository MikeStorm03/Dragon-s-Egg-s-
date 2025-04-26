package com.msg.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.msg.DragonsEggS;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Property;

@Mixin(FallingBlockEntity.class)
public class FallingBlockMixin {

    @Shadow private BlockState block;

    @Redirect(method = "tick()V",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/FallingBlockEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"))
    private ItemEntity inject(FallingBlockEntity entity, ItemConvertible item) {
        ItemStack stack = new ItemStack(item);
        if (stack.getItem() == Blocks.DRAGON_EGG.asItem()) {
            Map<String, String> props = new HashMap<>();
            for (Property<?> property : block.getProperties()) {
                Comparable<?> value = (Comparable<?>) block.get(property);
                props.put((property).getName(), value.toString());
            }
            
            DragonsEggS.LOGGER.info(stack.getItem() + " dropped");
            stack.set(DataComponentTypes.BLOCK_STATE, new BlockStateComponent(props));
        }
        return entity.dropStack(stack, 0);
    }
}
// ItemStack stack = new ItemStack(item)
