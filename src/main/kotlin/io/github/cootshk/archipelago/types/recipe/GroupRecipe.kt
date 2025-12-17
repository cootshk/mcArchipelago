package io.github.cootshk.archipelago.types.recipe

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.impl.APRecipe
import net.minecraft.resources.Identifier
import net.minecraft.world.item.crafting.RecipeHolder

class GroupRecipe internal constructor(var id: Int, trackingAdvancement: String, var namespaceIDs: Array<String>) :
    APRecipe {
    var trackingAdvancement: Identifier = Identifier.fromNamespaceAndPath(Archipelago.MODID, "received/$trackingAdvancement")
    override var grantedRecipes: MutableSet<RecipeHolder<*>> = HashSet()

    fun addIRecipe(iRecipe: RecipeHolder<*>) {
        this.grantedRecipes.add(iRecipe)
    }

    override val unlockedTrackingAdvancements: MutableSet<Identifier>
        get() = mutableSetOf(trackingAdvancement)
}