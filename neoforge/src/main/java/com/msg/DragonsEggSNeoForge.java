package com.msg;

import com.msg.ulti.DragonsEggSGameRule;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonsEggSConstants.ID)
public class DragonsEggSNeoForge {

    public DragonsEggSNeoForge(IEventBus eventBus) {

        DragonsEggSGameRule.MAX_GENRATION = GameRules.register("maxEggsGeneration",
                                                                GameRules.Category.MISC,
                                                                GameRules.IntegerValue.create(1));
        DragonsEggSGameRule.CONTINUE_SPAWN = GameRules.register("continueWhenMax",
                                                                GameRules.Category.MISC,
                                                                GameRules.BooleanValue.create(false));

        DragonsEggSConstants.LOG.info("Loading Dragon's Egg(S)");
        DragonsEggSCommon.init();

    }
}