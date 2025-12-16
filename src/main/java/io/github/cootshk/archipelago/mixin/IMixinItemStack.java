package io.github.cootshk.archipelago.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;

@Mixin(ItemStack.class)
public interface IMixinItemStack {
    @Unique
    void archipelago$setLore(Collection<String> lore);
}
