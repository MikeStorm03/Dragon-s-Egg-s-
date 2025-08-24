package com.msg.dragons_eggs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.msg.dragons_eggs.DragonsEggSConstants;

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
}