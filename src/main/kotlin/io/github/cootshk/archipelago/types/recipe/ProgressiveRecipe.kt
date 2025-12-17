package io.github.cootshk.archipelago.types.recipe

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.impl.APRecipe
import net.minecraft.resources.Identifier
import net.minecraft.world.item.crafting.RecipeHolder
import java.util.function.Consumer

class ProgressiveRecipe internal constructor(
    var id: Int,
    trackingAchievement: String,
    var namespaceIDs: ArrayList<Array<String>>
) : APRecipe {
    var trackingAdvancementBase: String = trackingAchievement
    var recipes: MutableList<MutableSet<RecipeHolder<*>>> = ArrayList()
    var currentTier: Int = 0
    var totalTiers: Int = namespaceIDs.size

    init {
        namespaceIDs.forEach(Consumer { namespaceID: Array<String> -> recipes.add(HashSet<RecipeHolder<*>>()) })
    }

    fun addIRecipe(iRecipe: RecipeHolder<*>, tier: Int) {
        this.recipes[tier].add(iRecipe)
    }

    fun getTier(tier: Int): MutableSet<RecipeHolder<*>> {
        if (recipes.size >= tier) return recipes[tier - 1]
        return recipes[recipes.size - 1]
    }


    override val grantedRecipes: MutableSet<RecipeHolder<*>>
        get() = getTier(++currentTier)

    override val unlockedTrackingAdvancements: MutableSet<Identifier>
        get() {
            val trackingAdvancements: HashSet<Identifier> = HashSet()
            for (i in 1..currentTier) {
                trackingAdvancements.add(
                    Identifier.fromNamespaceAndPath(
                        Archipelago.MODID,
                        "received/" + trackingAdvancementBase + "_" + i
                    )
                )
            }
            return trackingAdvancements
        }
}