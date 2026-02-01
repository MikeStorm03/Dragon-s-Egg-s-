package com.msg.dragons_eggs;

import com.msg.dragons_eggs.server.ServerSideCommon;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Constants.ID, dist = Dist.DEDICATED_SERVER)
public class ServerNeoForge {

    
    public ServerNeoForge(IEventBus eventBus) {

        ServerSideCommon.init();
    }
}