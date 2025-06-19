package com.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msg.platform.Services;
import com.msg.world.rules.MGameRules;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class DragonsEggSCommon {

	public static final Logger LOGGER = LoggerFactory.getLogger("Dragon's Egg(S)");

    public record CurrentEgg(Integer current_egg) implements CustomPacketPayload {
		public static final ResourceLocation CURRENT_EGG = ResourceLocation.fromNamespaceAndPath("dragon-egg-s", "current_egg");
		public static final CustomPacketPayload.Type<CurrentEgg> ID = new CustomPacketPayload.Type<>(CURRENT_EGG);
		public static final StreamCodec<FriendlyByteBuf, CurrentEgg> CODEC = StreamCodec.composite(
			ByteBufCodecs.INT, CurrentEgg::current_egg,
			CurrentEgg::new);
	
		@Override
		public Type<? extends CustomPacketPayload> type() {
			return ID;
		}
	}

    public static void init() {

        DragonsEggSConstants.LOG.info("Dragon's Egg(S) is currently on {} in {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        // DragonsEggSConstants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

		MGameRules.initialize();

        if (Services.PLATFORM.isModLoaded(DragonsEggSConstants.ID)) {

            DragonsEggSConstants.LOG.info("Dragon's Egg(S) loaded.");
        }
    }
}