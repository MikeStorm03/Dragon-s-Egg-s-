package com.msg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.Category;

public class DragonsEggSFabric implements ModInitializer {

    public static final GameRules.Key<GameRules.IntegerValue> MAX_GENRATION = GameRuleRegistry.register("maxEggsGeneration", Category.MISC, GameRuleFactory.createIntRule(1, 0, 1048575));
    public static final GameRules.Key<GameRules.BooleanValue> CONTINUE_SPAWN = GameRuleRegistry.register("continueWhenMax", Category.MISC, GameRuleFactory.createBooleanRule(false));
    
    @Override
    public void onInitialize() {
        DragonsEggSConstants.LOG.info("Dragon's Egg(S) running on Fabric.");
        DragonsEggSCommon.init();
    }
}