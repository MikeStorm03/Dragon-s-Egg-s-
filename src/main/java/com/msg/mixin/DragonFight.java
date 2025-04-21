package com.msg.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.msg.block.NewDragonEgg;
import com.msg.util.DragonEggSaveAndLoader;
import com.msg.world.MGameRules;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.gen.feature.EndPortalFeature;

@Mixin(EnderDragonFight.class)
public class DragonFight {

    @Shadow private UUID dragonUuid;
    @Shadow private boolean previouslyKilled;
    @Shadow private boolean dragonKilled;
    @Shadow private ServerBossBar bossBar;
    @Shadow private ServerWorld world;
    @Shadow private BlockPos origin;
    
    @Overwrite
    public void dragonKilled(EnderDragonEntity dragon) {
        if (dragon.getUuid().equals(this.dragonUuid)) {
            MinecraftServer server = world.getServer();
            assert server != null;
            DragonEggSaveAndLoader serverState = DragonEggSaveAndLoader.getServerState(server);
            this.bossBar.setPercent(0.0F);
            this.bossBar.setVisible(false);
            ((DragonFightInvoker) this).invokegenerateEndPortal(previouslyKilled);
            if (serverState.currentEggNumber < this.world.getGameRules().getInt(MGameRules.MAX_DRAGON_EGG)) {
                serverState.currentEggNumber = (serverState.currentEggNumber == NewDragonEgg.MAX_EGG) ? NewDragonEgg.MAX_EGG : serverState.currentEggNumber + 1;
                this.world.setBlockState(this.world.getTopPosition(Type.MOTION_BLOCKING, EndPortalFeature.offsetOrigin(this.origin)), Blocks.DRAGON_EGG.getDefaultState().with(NewDragonEgg.GENERATION, serverState.currentEggNumber));
            }
            
            this.previouslyKilled = true;
            this.dragonKilled = true;
        }
    }
}
