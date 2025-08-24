package com.msg.dragons_eggs;

import com.msg.dragons_eggs.ulti.DragonsEggSGameRule;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonsEggSConstants.ID)
public class DragonsEggSNeoForge {


    public DragonsEggSNeoForge(IEventBus eventBus) {

        DragonsEggSConstants.LOG.info("Loading Dragon's Egg(S)");
        DragonsEggSCommon.init();

        DragonsEggSGameRule.MAX_GENRATION = GameRules.register("maxEggsGeneration",
                                                                GameRules.Category.MISC,
                                                                GameRules.IntegerValue.create(1));
        DragonsEggSGameRule.CONTINUE_SPAWN = GameRules.register("continueWhenMax",
                                                                GameRules.Category.MISC,
                                                                GameRules.BooleanValue.create(false));

    }
}