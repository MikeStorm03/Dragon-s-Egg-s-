package com.msg.world;

import com.msg.DragonsEggS;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;

public class MGameRules {
    public static final GameRules.Key<GameRules.IntRule> MAX_GENRATION = GameRuleRegistry.register("maxEggsGeneration", Category.MISC, GameRuleFactory.createIntRule(1, 0, 1048575));
    public static final GameRules.Key<GameRules.BooleanRule> CONTINUE_SPAWN = GameRuleRegistry.register("continueWhenMax", Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static void initialize() {
        DragonsEggS.LOGGER.info("Registering maxDragonEggs Gamerule");
    }
}
