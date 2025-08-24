package com.msg.dragons_eggs;

import com.msg.dragons_eggs.ulti.DragonsEggSGameRule;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules.Category;

public class DragonsEggSFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        DragonsEggSGameRule.MAX_GENRATION = GameRuleRegistry.register("maxEggsGeneration", Category.MISC, GameRuleFactory.createIntRule(1, 0, 1048575));
        DragonsEggSGameRule.CONTINUE_SPAWN = GameRuleRegistry.register("continueWhenMax", Category.MISC, GameRuleFactory.createBooleanRule(false));

        DragonsEggSConstants.LOG.info("Dragon's Egg(S) running on Fabric.");
        DragonsEggSCommon.init();
    }
}