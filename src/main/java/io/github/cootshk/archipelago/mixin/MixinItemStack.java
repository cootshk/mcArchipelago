package io.github.cootshk.archipelago.mixin;

import io.github.cootshk.archipelago.impl.ItemStackMixin;
import io.github.cootshk.archipelago.managers.GoalManager;
import io.github.cootshk.archipelago.types.Boss;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements ItemStackMixin {
    @Unique
    private final Item[] wither_items = new Item[] {
            Items.WITHER_SKELETON_SKULL,
            Items.WITHER_ROSE,
    };
    @Unique
    private final Item[] dragon_items = new Item[] {
            Items.DRAGON_EGG,
            Items.ENDER_PEARL,
            Items.ENDER_EYE
    };

    @Unique
    private static final @NotNull ArrayList<String> lore = new ArrayList<>();
    @Contract(" -> new")
    @Unique
    private static @NotNull ItemLore generateLore() {
        return new ItemLore(lore.stream().map(Component::translatable).map((mc) -> (Component) mc).toList());
    }

    @Inject(
        method = "<init>(Lnet/minecraft/world/level/ItemLike;ILnet/minecraft/core/component/PatchedDataComponentMap;)V",
        at = @At("TAIL")
    )
    public void onItemStackInit(ItemLike itemLike, int count, PatchedDataComponentMap patchedDataComponentMap, CallbackInfo ci) {
//        archipelago$updateLore();
    }

    @Shadow
    @Final PatchedDataComponentMap components;

    @Shadow
    public abstract Item getItem();

    @Unique
    public void archipelago$updateLore() {
        // noinspection ConstantValue
        if (lore == null) return;

        LogManager.getLogger().info("[Archipelago] Lore: {}", lore);
        generateLore(this.getItem());
        if (lore.isEmpty()) {
            this.components.remove(DataComponents.LORE);
        } else {
            this.components.set(DataComponents.LORE, generateLore());
        }
    }

    @Unique
    private void generateLore(Item type) {
        lore.clear();
        if (GoalManager.isBossRequired(Boss.WITHER) && Arrays.asList(wither_items).contains(type)) {
            lore.add("archipelago:goal.wither.lore.required");
        } else if (GoalManager.isBossRequired(Boss.DRAGON) && Arrays.asList(dragon_items).contains(type)) {
            lore.add("archipelago:goal.dragon.lore.required");
        }
    }
}
