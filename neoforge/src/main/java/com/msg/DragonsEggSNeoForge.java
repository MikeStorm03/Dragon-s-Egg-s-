package com.msg;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonsEggSConstants.ID)
public class DragonsEggSNeoForge {

    public static final GameRules.Key<GameRules.IntegerValue> MAX_GENRATION = GameRules.register("maxEggsGeneration",
                                                                                                GameRules.Category.MISC,
                                                                                                GameRules.IntegerValue.create(1));
    public static final GameRules.Key<GameRules.BooleanValue> CONTINUE_SPAWN = GameRules.register("continueWhenMax",
                                                                                                GameRules.Category.MISC,
                                                                                                GameRules.BooleanValue.create(false));


    public DragonsEggSNeoForge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        DragonsEggSConstants.LOG.info("Loading Dragon's Egg(S)");
        DragonsEggSCommon.init();

    }
}