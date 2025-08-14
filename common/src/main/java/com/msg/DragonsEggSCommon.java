package com.msg;

import com.msg.platform.Services;
import com.msg.server.ServerSideCommon;

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