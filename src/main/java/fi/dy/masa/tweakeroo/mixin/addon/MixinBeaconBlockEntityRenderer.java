package fi.dy.masa.tweakeroo.mixin.addon;

import fi.dy.masa.tweakeroo.config.FeatureToggle;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntityRenderer.class)
public abstract class MixinBeaconBlockEntityRenderer {
    @Inject(
            method = "render(Lnet/minecraft/block/entity/BeaconBlockEntity;DDDFI)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableBeaconBeam(BeaconBlockEntity beaconBlockEntity, double d, double e, double f, float g, int i, CallbackInfo ci) {
        if (FeatureToggle.DISABLE_BEACON_BEAM.getBooleanValue()) {
            ci.cancel();
        }
    }
}
