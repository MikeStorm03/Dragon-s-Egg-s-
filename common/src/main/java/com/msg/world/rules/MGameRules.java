package com.msg.world.rules;

import com.msg.DragonsEggSCommon;
import com.msg.DragonsEggSConstants;
import com.msg.block.NewDragonEgg;
import com.msg.mixin.BooleanValueAccessor;
import com.msg.mixin.GamerulesAccessor;
import com.msg.mixin.IntegerValueAccessor;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.GameRules.*;

public class MGameRules {

    // public static final Key<IntegerValue> MAX_GENRATION = GamerulesAccessor.register("maxEggsGeneration", GameRulesCategoryMixin.DRAGONS_EGGS, intRule(1, 0, NewDragonEgg.MAX_EGG));

    public static final Key<IntegerValue> MAX_GENRATION = GamerulesAccessor.register("maxEggsGeneration", Category.MISC, intRule(1, 0, NewDragonEgg.MAX_EGG));
    public static final Key<BooleanValue> CONTINUE_SPAWN = GamerulesAccessor.register("continueWhenMax", Category.MISC, boolRule(false));

    static Type<IntegerValue> intRule(int defaultValue, int min, int max){
        return IntegerValueAccessor.create(defaultValue, min, max, FeatureFlagSet.of(), (minecraftServer, integerValue) -> {});
    }
    static Type<BooleanValue> boolRule(boolean defaultValue){
        return BooleanValueAccessor.create(defaultValue, (minecraftServer, integerValue) -> {});
    }

    public static void initialize() {
        DragonsEggSCommon.LOGGER.info("Registering "+ DragonsEggSConstants.NAME +" Gamerules");
    }
}