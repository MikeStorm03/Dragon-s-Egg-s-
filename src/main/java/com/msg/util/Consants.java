package com.msg.util;

import net.minecraft.state.property.IntProperty;

public interface Consants {
    public static int MAX_EGG = 100; // 1048575 for release
    public static final IntProperty GENERATION = IntProperty.of("generation", 0, MAX_EGG);
}
