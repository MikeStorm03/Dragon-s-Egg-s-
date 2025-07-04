package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.msg.DragonsEggSConstants;

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

    @Inject(method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour/Properties;I)V", at = @At("TAIL"))
    public void inject(Properties properties, CallbackInfo ci) {
        registerDefaultState(defaultBlockState().setValue(DragonsEggSConstants.GENERATION, 0));
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