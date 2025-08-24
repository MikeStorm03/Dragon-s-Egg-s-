package com.msg.dragons_eggs.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.msg.dragons_eggs.DragonsEggSConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;

@Mixin(BlockItemStateProperties.class)
public class BlockItemStatePropertiesMixin {
    @Inject(method = "Lnet/minecraft/world/item/component/BlockItemStateProperties;addToTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V", at = @At("TAIL"))
    public void dragonEggGeneration(TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag,
            DataComponentGetter dataComponentGetter, CallbackInfo ci) {
        BlockItemStateProperties props = dataComponentGetter.get(DataComponents.BLOCK_STATE);
        if (props != null) {
            Integer generation = props.get(DragonsEggSConstants.GENERATION);
            if (generation != null){
                consumer.accept(Component.translatable("dragon_egg.generation",  generation)
                        .withStyle(ChatFormatting.GOLD));
            }
        }
    }
}
