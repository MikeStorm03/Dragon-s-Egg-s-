package com.msg;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonsEggSConstants.ID)
public class DragonsEggS {

    public DragonsEggS(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        DragonsEggSConstants.LOG.info("Loading Dragon's Egg(S)");
        DragonsEggSCommon.init();

    }
}