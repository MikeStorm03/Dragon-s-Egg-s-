package com.msg.dragons_eggs;

import com.msg.dragons_eggs.platform.Services;
import com.msg.dragons_eggs.server.ServerSideCommon;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class DragonsEggSCommon {

    public static void init() {
        DragonsEggSConstants.LOG.info("Mod {} is running on {}! we are currently in a {} environment on {} side!",
										DragonsEggSConstants.NAME,
										Services.PLATFORM.getPlatformName(),
										Services.PLATFORM.getEnvironmentName(),
										Services.PLATFORM.getEnvironmentType());

		if (Services.PLATFORM.isServerSide()) {
			ServerSideCommon.init();
		}
    }
}