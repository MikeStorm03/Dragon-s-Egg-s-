package com.msg.mixin;

import java.util.ArrayList;
import java.util.Arrays;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.GameRules.Category;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

@Mixin(Category.class)
public abstract class GameRulesCategoryMixin {

    @Shadow
	@Final
	@Mutable
	private static Category[] $VALUES;

    private static final Category DRAGONS_EGGS = addCategory("DRAGONS_EGGS","Dragon's Egg(S)");

    @Invoker("<init>")
	public static Category invoke(String valueName, int ordinal, String description) {
		throw new AssertionError("Dragon's Egg(S) gamerules invoke failed.");
	}

    private static Category addCategory(String internalName, String description) {
        ArrayList<Category> variants = new ArrayList<Category>(Arrays.asList($VALUES));
        Category categories = invoke(internalName, variants.get(variants.size() - 1).ordinal() + 1, description);
        variants.add(categories);
        $VALUES = variants.toArray(new Category[0]);
        return categories;
	}

}