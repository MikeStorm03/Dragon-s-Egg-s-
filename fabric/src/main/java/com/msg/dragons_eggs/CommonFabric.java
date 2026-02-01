package com.msg.dragons_eggs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class CommonFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Common.init();
        Common.registerGamerules();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(Common.registerCommand()));
    }
}