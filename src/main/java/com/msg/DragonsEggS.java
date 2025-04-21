package com.msg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.msg.world.MGameRules;

public class DragonsEggS implements ModInitializer {

	public static final String MOD_ID = "msg";
	public static final Logger LOGGER = LoggerFactory.getLogger("Dragon's Egg(S)");

	public record CurrentEgg(Integer current_egg) implements CustomPayload {
		public static final Identifier CURRENT_EGG = Identifier.of("dragon-egg-s", "current_egg");
		public static final CustomPayload.Id<CurrentEgg> ID = new CustomPayload.Id<>(CURRENT_EGG);
		public static final PacketCodec<PacketByteBuf, CurrentEgg> CODEC = PacketCodec.tuple(
				PacketCodecs.INTEGER, CurrentEgg::current_egg,
				CurrentEgg::new);
	
		@Override
		public Id<? extends CustomPayload> getId() {
			return ID;
		}
	}

	@Override
	public void onInitialize() {
        MGameRules.initialize();
		PayloadTypeRegistry.playS2C().register(CurrentEgg.ID, CurrentEgg.CODEC);
	}
	
}