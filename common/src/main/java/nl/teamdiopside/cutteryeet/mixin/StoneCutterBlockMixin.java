package nl.teamdiopside.cutteryeet.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.teamdiopside.cutteryeet.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StonecutterBlock.class)
public abstract class StoneCutterBlockMixin extends Block {
    public StoneCutterBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!Config.enabled || entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        } else {
            entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());
        }
    }
}
