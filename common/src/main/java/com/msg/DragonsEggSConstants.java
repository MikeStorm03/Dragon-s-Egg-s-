package com.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DragonsEggSConstants {

	public static final String NAMESPACE = "msg";
	public static final String ID = "dragons_eggs";
	public static final String NAME = "Dragon's Egg(S)";
    public static final int MAX_EGG = 1048575;
    public static final IntegerProperty GENERATION = IntegerProperty.create("generation", 0, MAX_EGG); 
	public static final Logger LOG = LoggerFactory.getLogger(NAME);
}