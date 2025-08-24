package com.msg.dragons_eggs.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ServerSideCommon {
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

    public static void init() {}
}
