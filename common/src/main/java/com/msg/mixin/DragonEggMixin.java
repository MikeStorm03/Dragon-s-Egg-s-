package com.msg.mixin;

import java.util.List;
import java.util.Objects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.msg.DragonsEggSConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

@Mixin(DragonEggBlock.class)
public class DragonEggMixin extends Block{
    
    public DragonEggMixin(Properties properties) {
        super(properties);
    }

    @Unique
    private static final int MAX_EGG = DragonsEggSConstants.MAX_EGG; 
    @Unique
    private static final IntegerProperty GENERATION = DragonsEggSConstants.GENERATION;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GENERATION);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag options) {
        super.appendHoverText(stack, context, tooltip, options);
        BlockItemStateProperties blockStateComponent = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        int i = (Integer)Objects.requireNonNullElse((Integer)blockStateComponent.get(GENERATION), 0);
        tooltip.add(Component.translatable("dragon_egg.generation", new Object[]{i}).withStyle(ChatFormatting.GOLD));
   }

}