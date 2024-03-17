package nl.teamdiopside.cutteryeet.mixin;

import dev.architectury.platform.Platform;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nl.teamdiopside.cutteryeet.CutterYeet;
import nl.teamdiopside.cutteryeet.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void tickMovement(CallbackInfo ci) {
        if ((LivingEntity) (Object) this instanceof PlayerEntity player && player.getAbilities().flying) {
            return;
        }

        BlockState blockState = getBlockStateAtPos();
        if (!isSneaking() && isStonecutter(blockState)) {
            // Fixes inconsistencies
            this.setOnGround(false);

            Direction direction = blockState.get(Properties.HORIZONTAL_FACING);
            Vec3d pushVelocity = Vec3d.of(direction.rotateCounterclockwise(Direction.Axis.Y).getVector()).add(0, 0.3, 0).multiply(Config.yeetStrength);
            this.setVelocity(pushVelocity);

            this.playSound(CutterYeet.CUT_SOUND.get(), 1f, 1f);
        }
    }

    @Unique
    public boolean isStonecutter(BlockState blockState) {
        boolean isInsideModdedCutter = false;
        if (Platform.isModLoaded("corail_woodcutter")) {
            Block block = Registries.BLOCK.get(new Identifier("corail_woodcutter", "oak_woodcutter"));
            if (blockState.getBlock().getClass() == block.getClass()) {
                isInsideModdedCutter = true;
            }
        }
        return blockState.getBlock() instanceof StonecutterBlock || isInsideModdedCutter;
    }
}
