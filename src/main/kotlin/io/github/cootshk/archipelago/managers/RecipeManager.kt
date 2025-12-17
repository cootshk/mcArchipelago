package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.data.RecipeData
import net.minecraft.resources.Identifier
import net.minecraft.world.item.crafting.RecipeHolder
import java.util.function.Consumer

object RecipeManager {
    // have a lookup of every advancement
    private val recipeData: RecipeData = RecipeData()

    private val initialRestricted: MutableSet<RecipeHolder<*>> = HashSet()
    private val initialGranted: MutableSet<RecipeHolder<*>> = HashSet()

    var restrictedRecipes: MutableSet<RecipeHolder<*>> = HashSet()
        private set
    var grantedRecipes: MutableSet<RecipeHolder<*>> = HashSet()
        private set

    private val itemAdvancements: MutableSet<Identifier> = HashSet()


    init {
        val recipeList: MutableCollection<RecipeHolder<*>> = Archipelago.server.recipeManager.recipes
        for (iRecipe in recipeList) {
            if (recipeData.injectIRecipe(iRecipe)) {
                initialRestricted.add(iRecipe)
            } else {
                initialGranted.add(iRecipe)
            }
        }
        this.restrictedRecipes = initialRestricted
        this.grantedRecipes = initialGranted
    }

    fun grantRecipeList(recipes: MutableList<Long>) {
        for (id in recipes) {
            if (!recipeData.hasID(id)) continue
            grantRecipe(id)
            return
        }
    }

    fun grantRecipe(id: Long) {
        if (!recipeData.hasID(id)) return
        val toGrant: MutableSet<RecipeHolder<*>> = recipeData.getID(id)!!.grantedRecipes

        grantedRecipes.addAll(toGrant)
        restrictedRecipes.removeAll(toGrant)
        itemAdvancements.addAll(recipeData.getID(id)!!.unlockedTrackingAdvancements)

        for (player in Archipelago.server.playerList.players) {
            //player.resetRecipes(restricted);
            player.awardRecipes(this.grantedRecipes)

            val serverAdvancements = Archipelago.server.advancements
            recipeData.getID(id)!!.unlockedTrackingAdvancements.forEach(
                Consumer { location: Identifier ->
                    val trackingAdvancement = serverAdvancements.get(location)
                    if (trackingAdvancement != null) {
                        AdvancementManager.syncAdvancement(trackingAdvancement)
                    }
                })
        }
    }

    fun resetRecipes() {
        this.restrictedRecipes = initialRestricted
        this.grantedRecipes = initialGranted
        recipeData.reset()
    }

    fun hasReceived(id: Identifier): Boolean {
        return itemAdvancements.contains(id)
    }
}
