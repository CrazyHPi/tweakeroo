package fi.dy.masa.tweakeroo.mixin.addon;

import fi.dy.masa.tweakeroo.config.Configs;
import fi.dy.masa.tweakeroo.config.FeatureToggle;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelProperties.class)
public abstract class MixinLevelProperties {
    @Inject(
            method = "getTimeOfDay",
            at = @At("HEAD"),
            cancellable = true
    )
    private void clientTimeOverride(CallbackInfoReturnable<Long> cir) {
        if(FeatureToggle.CLIENT_TIME_OVERRIDE.getBooleanValue()) {
            cir.setReturnValue((long) Configs.Generic.CLIENT_TIME.getIntegerValue());
        }
    }
}
