package com.msg.dragons_eggs;

import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.ID)
@EventBusSubscriber(modid = Constants.ID)
public class CommonNeoForge {

    public CommonNeoForge() {
        Common.init();
    }

    @SubscribeEvent
    static void registerSetup(RegisterEvent event) {
        if (event.getRegistry().equals(BuiltInRegistries.GAME_RULE)) {
            Common.registerGamerules();
        }
    }
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Common.registerCommand());
    }
}