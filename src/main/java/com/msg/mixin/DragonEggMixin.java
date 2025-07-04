package com.msg.mixin;

import java.util.List;
import java.util.Objects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.msg.util.Consants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(DragonEggBlock.class)
public class DragonEggMixin extends Block {
    
    public DragonEggMixin(Settings settings) {
        super(settings);
    }

    @Unique
    private static final int MAX_EGG = Consants.MAX_EGG; 
    @Unique
    private static final IntProperty GENERATION = Consants.GENERATION;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GENERATION);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
      super.appendTooltip(stack, context, tooltip, options);
      tooltip.add(Text.translatable("dragon_egg.generation", new Object[]{(Integer)Objects.requireNonNullElse((Integer)((BlockStateComponent)stack.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT)).getValue(GENERATION), 0)})
                     .formatted(Formatting.GOLD));
   }
}
