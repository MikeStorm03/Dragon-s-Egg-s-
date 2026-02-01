package com.msg.dragons_eggs;

import org.jetbrains.annotations.NotNull;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.serialization.Codec;
import com.msg.dragons_eggs.platform.Services;
import com.msg.dragons_eggs.server.DragonEggSaveAndLoader;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class Common {

    public static GameRule<@NotNull Integer> MAX_GENRATION;
    public static GameRule<@NotNull Boolean> CONTINUE_SPAWN;

	//     public record CurrentEgg(Boolean hasMod) implements CustomPacketPayload {
	// 	public static final Identifier HAS_MOD = Identifier.fromNamespaceAndPath("dragon-egg-s", "hasMod");
	// 	public static final CustomPacketPayload.Type<CurrentEgg> ID = new CustomPacketPayload.Type<>(HAS_MOD);
	// 	public static final StreamCodec<FriendlyByteBuf, CurrentEgg> CODEC = StreamCodec.composite(
	// 		ByteBufCodecs.BOOL, CurrentEgg::hasMod,
	// 		CurrentEgg::new);
	
	// 	@Override
	// 	public Type<? extends CustomPacketPayload> type() {
	// 		return ID;
	// 	}
	// }

    public static void init() {
        Constants.LOG.info("Mod {} is running on {}! we are currently in a {} environment on {} side!",
										Constants.NAME,
										Services.PLATFORM.getPlatformName(),
										Services.PLATFORM.getEnvironmentName(),
										Services.PLATFORM.getEnvironmentType());
    }

	public static void registerGamerules(){
		MAX_GENRATION = Registry.register(BuiltInRegistries.GAME_RULE,
											"max_egg_generation",
											(GameRule<Integer>) new GameRule(GameRuleCategory.MISC,
											GameRuleType.INT,
											IntegerArgumentType.integer(0, 1048575),
											GameRuleTypeVisitor::visitInteger,
											Codec.intRange(0, 1048575),
											(integer) -> { return (int) integer; },
											1,
                                            FeatureFlagSet.of()));
		CONTINUE_SPAWN = Registry.register(BuiltInRegistries.GAME_RULE,
											"continue_when_max",
											(GameRule<Boolean>) new GameRule(GameRuleCategory.MISC,
                                            GameRuleType.BOOL,
                                            BoolArgumentType.bool(),
                                            GameRuleTypeVisitor::visitBoolean,
                                            Codec.BOOL,
                                            (boolean_) -> { return (Boolean) boolean_ ? 1 : 0; },
                                            false,
                                            FeatureFlagSet.of()));
	}
	public static LiteralArgumentBuilder<CommandSourceStack> registerCommand() {
		return Commands.literal("latest_egg_generation")
            .executes(context -> {
                context.getSource().sendSuccess(() -> Component.translatable("latest_dragons_egg_generation", DragonEggSaveAndLoader.getServerState(context.getSource().getServer()).currentEggNumber), false);
                return 1;
            });
	}
}