package com.msg.block;

import java.util.List;
import java.util.Objects;

import com.mojang.serialization.MapCodec;

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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class NewDragonEgg extends DragonEggBlock{

   public static final MapCodec<DragonEggBlock> CODEC = createCodec(NewDragonEgg::new);
   public static final int MAX_EGG = 1048575; // 1048575 for release
   public static final IntProperty GENERATION = IntProperty.of("generation", 0, MAX_EGG); 

   public MapCodec<DragonEggBlock> getCodec() {
      return CODEC;
   }

   public NewDragonEgg(Settings settings) {
      super(settings);
      setDefaultState(getDefaultState().with(GENERATION, 0));
   }

   @Override
   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(GENERATION);
   }

   public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
      super.appendTooltip(stack, context, tooltip, options);
      BlockStateComponent blockStateComponent = (BlockStateComponent)stack.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT);
      int i = (Integer)Objects.requireNonNullElse((Integer)blockStateComponent.getValue(GENERATION), 0);
      tooltip.add(Text.translatable("dragon_egg.generation", new Object[]{i}).formatted(Formatting.GOLD));
   }

}