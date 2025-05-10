package com.msg;

import java.util.Objects;

import com.msg.block.NewDragonEgg;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DragonsEggSClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
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