package io.github.cootshk.archipelago.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Overwrite
    public boolean allowsTelemetry() {
        return false;
    }
}
