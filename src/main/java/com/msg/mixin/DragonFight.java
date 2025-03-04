package com.msg.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.msg.block.NewDragonEgg;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
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
    @Unique private int eggCycle = 0;
    @Overwrite
    public void dragonKilled(EnderDragonEntity dragon) {
        if (dragon.getUuid().equals(this.dragonUuid)) {
            this.bossBar.setPercent(0.0F);
            this.bossBar.setVisible(false);
            this.eggCycle = (this.eggCycle == NewDragonEgg.MAX_EGG_CYCLE) ? NewDragonEgg.MAX_EGG_CYCLE : this.eggCycle + 1;
            ((DragonFightInvoker) this).invokegenerateEndPortal(previouslyKilled);
            this.world.setBlockState(this.world.getTopPosition(Type.MOTION_BLOCKING, EndPortalFeature.offsetOrigin(this.origin)), Blocks.DRAGON_EGG.getDefaultState().with(NewDragonEgg.GENERATION, eggCycle));
  
            this.previouslyKilled = true;
            this.dragonKilled = true;
        }
    }
}
