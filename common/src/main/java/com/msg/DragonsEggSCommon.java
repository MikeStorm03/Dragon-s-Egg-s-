package com.msg;

import com.msg.platform.DragonsEggSPlatformServices;
import com.msg.server.ServerSideCommon;

public class DragonsEggSCommon {

    public static void init() {
	
		DragonsEggSConstants.LOG.info("Mod {} is running on {}! we are currently in a {} environment on {} side!",
										DragonsEggSConstants.NAME,
										DragonsEggSPlatformServices.PLATFORM.getPlatformName(),
										DragonsEggSPlatformServices.PLATFORM.getEnvironmentName(),
										DragonsEggSPlatformServices.PLATFORM.getEnvironmentType());

		if (DragonsEggSPlatformServices.PLATFORM.isServerSide()) {
			ServerSideCommon.init();
		}
	}
}