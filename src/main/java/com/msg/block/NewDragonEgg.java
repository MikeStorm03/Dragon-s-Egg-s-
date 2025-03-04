package com.msg.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.DragonEggBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class NewDragonEgg extends DragonEggBlock{

   public static final MapCodec<DragonEggBlock> CODEC = createCodec(NewDragonEgg::new);
   public static final IntProperty GENERATION = IntProperty.of("generation", 0, 65534);

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

}