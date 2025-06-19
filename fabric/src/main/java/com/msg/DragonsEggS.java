package com.msg;

import net.fabricmc.api.ModInitializer;

public class DragonsEggS implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        DragonsEggSConstants.LOG.info("Dragon's Egg(S) running on Fabric.");
        DragonsEggSCommon.init();
    }
}
