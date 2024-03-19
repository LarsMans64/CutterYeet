package nl.teamdiopside.cutteryeet.mixin;

import dev.architectury.platform.Platform;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.entity.EntityLike;
import nl.teamdiopside.cutteryeet.CutterYeet;
import nl.teamdiopside.cutteryeet.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput {

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickMovement(CallbackInfo ci) {
        if (!Config.enabled) {
            return;
        }

        Entity self = (Entity) (Object) this;
        if ((self instanceof PlayerEntity player && player.getAbilities().flying) || !(self instanceof LivingEntity || self instanceof BoatEntity || self instanceof ItemEntity)) {
            return;
        }

        BlockState blockState = self.getBlockStateAtPos();
        if (!self.isSneaking() && cutterYeet$isStonecutter(blockState)) {
            // Fixes inconsistencies
            self.setOnGround(false);

            Direction direction = blockState.get(Properties.HORIZONTAL_FACING);
            Vec3d pushVelocity = Vec3d.of(direction.rotateCounterclockwise(Direction.Axis.Y).getVector()).add(0, 0.3, 0).multiply(self instanceof ItemEntity ? 0.65 * Config.yeetStrength : Config.yeetStrength);
            self.setVelocity(pushVelocity);

            self.playSound(CutterYeet.CUT_SOUND.get(), 1f, 1f);
        }
    }

    @Unique
    public boolean cutterYeet$isStonecutter(BlockState blockState) {
        if (Platform.isModLoaded("corail_woodcutter")) {
            Block block = Registries.BLOCK.get(new Identifier("corail_woodcutter", "oak_woodcutter"));
            if (blockState.getBlock().getClass() == block.getClass()) {
                return true;
            }
        }
        return blockState.getBlock() instanceof StonecutterBlock;
    }
}
