package com.msg.dragons_eggs;

import com.msg.dragons_eggs.platform.Services;
import com.msg.dragons_eggs.server.ServerSideCommon;

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