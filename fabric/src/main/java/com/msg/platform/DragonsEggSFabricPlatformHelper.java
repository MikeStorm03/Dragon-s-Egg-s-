package com.msg.platform;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class DragonsEggSFabricPlatformHelper implements DragonsEggSPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isServerSide() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER);
    }
}