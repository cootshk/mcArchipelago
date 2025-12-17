package io.github.cootshk.archipelago.impl

import net.minecraft.resources.Identifier
import net.minecraft.world.item.crafting.RecipeHolder

interface APRecipe {
    val grantedRecipes: MutableSet<RecipeHolder<*>>

    val unlockedTrackingAdvancements: MutableSet<Identifier>
}