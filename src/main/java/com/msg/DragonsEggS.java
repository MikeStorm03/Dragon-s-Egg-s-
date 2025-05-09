package com.msg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msg.block.NewDragonEgg;
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
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
    		if (!itemStack.isOf(Blocks.DRAGON_EGG.asItem())) {
        		return;
    		}
			BlockStateComponent blockStateComponent = (BlockStateComponent)itemStack.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT);
			int i = (Integer)Objects.requireNonNullElse((Integer)blockStateComponent.getValue(NewDragonEgg.GENERATION), 0);
			list.add(Text.translatable("dragon_egg.generation", new Object[]{i}).formatted(Formatting.GOLD));
		});
	}
	
}