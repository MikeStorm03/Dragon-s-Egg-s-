package com.msg.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class NewDragonEgg extends DragonEggBlock{

    public static final MapCodec<NewDragonEgg> CODEC = simpleCodec(NewDragonEgg::new);
    public static final int MAX_EGG = 1048575; // 1048575 for Fabric and NeoForge     65535 for Forge 
    public static final IntegerProperty GENERATION = IntegerProperty.create("generation", 0, MAX_EGG); 

    public MapCodec<NewDragonEgg> getCodec() {
        return CODEC;
    }

    public NewDragonEgg(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GENERATION, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GENERATION);
    }
}