package com.msg.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.msg.DragonEggSaveAndLoader;
import com.msg.DragonsEggSConstants;
import com.msg.DragonsEggSNeoForge;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;

@Mixin(EndDragonFight.class)
public class DragonFightMixin {

    @Shadow private UUID dragonUUID;
    @Shadow private boolean previouslyKilled;
    @Shadow private boolean dragonKilled;
    @Shadow private ServerBossEvent dragonEvent;
    @Shadow private ServerLevel level;
    @Shadow private BlockPos origin;
    
    @Overwrite
    public void setDragonKilled(EnderDragon dragon) {
        if (dragon.getUUID().equals(this.dragonUUID)) {
            MinecraftServer server = level.getServer();
            assert server != null;
            DragonEggSaveAndLoader serverState = DragonEggSaveAndLoader.getServerState(server);
            this.dragonEvent.setProgress(0.0F);
            this.dragonEvent.setVisible(false);
            ((DragonFightInvoker) this).invokegenerateEndPortal(previouslyKilled);
            if (serverState.currentEggNumber < this.level.getGameRules().getInt(DragonsEggSNeoForge.MAX_GENRATION)) {
                serverState.currentEggNumber += 1;
                this.level.setBlockAndUpdate(this.level.getHeightmapPos(Types.MOTION_BLOCKING, EndPodiumFeature.getLocation(this.origin)), Blocks.DRAGON_EGG.defaultBlockState().setValue(DragonsEggSConstants.GENERATION, serverState.currentEggNumber));
            } else if (this.level.getGameRules().getBoolean(DragonsEggSNeoForge.CONTINUE_SPAWN)) {
                this.level.setBlockAndUpdate(this.level.getHeightmapPos(Types.MOTION_BLOCKING, EndPodiumFeature.getLocation(this.origin)), Blocks.DRAGON_EGG.defaultBlockState().setValue(DragonsEggSConstants.GENERATION, serverState.currentEggNumber));
            }
            
            this.previouslyKilled = true;
            this.dragonKilled = true;
        }
    }
}