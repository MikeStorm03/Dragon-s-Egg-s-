package com.msg.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.msg.DragonsEggSConstants;
import com.msg.server.DragonEggSaveAndLoader;
import com.msg.ulti.DragonsEggSGameRule;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;

@Mixin(EndDragonFight.class)
public class DragonFightMixin {

    @Shadow private ServerLevel level;
    @Shadow private BlockPos origin;

    @Redirect(method = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;setDragonKilled(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;)V",
                at = @At(value = "FIELD",
                        target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;previouslyKilled:Z",
                        opcode = Opcodes.GETFIELD))
    private boolean inject(EndDragonFight endDragonFight){
        DragonEggSaveAndLoader serverState = DragonEggSaveAndLoader.getServerState(level.getServer());
        if (serverState.currentEggNumber < this.level.getGameRules().getInt(DragonsEggSGameRule.MAX_GENRATION)) {
                serverState.currentEggNumber += 1;
                this.level.setBlockAndUpdate(this.level.getHeightmapPos(Types.MOTION_BLOCKING, EndPodiumFeature.getLocation(this.origin)), Blocks.DRAGON_EGG.defaultBlockState().setValue(DragonsEggSConstants.GENERATION, serverState.currentEggNumber));
            } else if (this.level.getGameRules().getBoolean(DragonsEggSGameRule.CONTINUE_SPAWN)) {
                this.level.setBlockAndUpdate(this.level.getHeightmapPos(Types.MOTION_BLOCKING, EndPodiumFeature.getLocation(this.origin)), Blocks.DRAGON_EGG.defaultBlockState().setValue(DragonsEggSConstants.GENERATION, serverState.currentEggNumber));
            }
        return true;
    }
}