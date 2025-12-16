package io.github.cootshk.archipelago.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.cootshk.archipelago.managers.GoalManager;
import io.github.cootshk.archipelago.types.Boss;
import kotlin.NotImplementedError;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {
    @Unique
    private ItemStack stack() { return (ItemStack)(Object)this; }

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

    @ModifyExpressionValue(
        method = "<init>(Lnet/minecraft/world/level/ItemLike;ILnet/minecraft/core/component/PatchedDataComponentMap;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/ItemLike;asItem()Lnet/minecraft/world/item/Item;"
        )
    )
    public Item onItemStackItemAssignInInit(Item original) {
        ArrayList<String> lore = new ArrayList<>();
        if (GoalManager.isBossRequired(Boss.WITHER) && Arrays.stream(wither_items).anyMatch((i) -> i == original)) {
            lore.add("todo: wither message here");
        } else if (GoalManager.isBossRequired(Boss.DRAGON) && Arrays.stream(dragon_items).anyMatch((i) -> i == original)) {
            lore.add("todo: dragon message here");
        }
        archipelago$setLore(lore);
        return original;
    }

    @Override
    @Unique
    public void archipelago$setLore(Collection<String> lore) {
        throw new NotImplementedError("remove this and just modify the Item class directly");
    }
}
