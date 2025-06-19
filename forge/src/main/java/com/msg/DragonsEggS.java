package com.msg;

import net.minecraftforge.fml.common.Mod;

@Mod(DragonsEggSConstants.ID)
public class DragonsEggS {

    public DragonsEggS() {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        DragonsEggSConstants.LOG.info("Loading Dragon's Egg(S)");
        DragonsEggSCommon.init();

    }
}