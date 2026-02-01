package com.msg.dragons_eggs;

import com.msg.dragons_eggs.server.ServerSideCommon;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class ServerFabric implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        ServerSideCommon.init();
    }
}