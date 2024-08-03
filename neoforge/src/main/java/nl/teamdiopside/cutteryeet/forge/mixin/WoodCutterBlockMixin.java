package nl.teamdiopside.cutteryeet.forge.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.teamdiopside.cutteryeet.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import ovh.corail.woodcutter.block.WoodcutterBlock;

@Mixin(WoodcutterBlock.class)
public abstract class WoodCutterBlockMixin extends HorizontalFacingBlock implements FluidDrainable, FluidFillable {
    protected WoodCutterBlockMixin(Settings arg) {
        super(arg);
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
