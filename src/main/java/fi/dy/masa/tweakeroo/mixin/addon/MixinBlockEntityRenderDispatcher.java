package fi.dy.masa.tweakeroo.mixin.addon;

import fi.dy.masa.tweakeroo.config.FeatureToggle;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockEntityRenderDispatcher.class)
public abstract class MixinBlockEntityRenderDispatcher {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/BlockEntity;getSquaredRenderDistance()D"
            )
    )
    private double renderTileEntitiesOverride(BlockEntity instance) {
        if (FeatureToggle.TILE_ENTITIES_RENDER_DISTANCE_OVERRIDE.getBooleanValue()) {
            return Double.MAX_VALUE;
        }
        return instance.getSquaredRenderDistance();
    }
}
