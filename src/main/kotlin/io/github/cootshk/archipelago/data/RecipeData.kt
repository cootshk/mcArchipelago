package io.github.cootshk.archipelago.data

import io.github.cootshk.archipelago.impl.APRecipe
import io.github.cootshk.archipelago.types.recipe.GroupRecipe
import io.github.cootshk.archipelago.types.recipe.ProgressiveRecipe
import net.minecraft.world.item.crafting.RecipeHolder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.ArrayList
import java.util.HashMap

// https://github.com/KonoTyran/Minecraft_AP_Randomizer/blob/master/src/main/java/gg/archipelago/aprandomizer/managers/recipemanager/RecipeData.java#L18
class RecipeData {
    var recipes: HashMap<Long, GroupRecipe> = object : HashMap<Long, GroupRecipe>() {
        init {
            put(
                45000L, GroupRecipe(
                    45000, "archery", arrayOf(
                        "minecraft:bow",
                        "minecraft:arrow",
                        "minecraft:crossbow"
                    )
                )
            )
            //put(45001, new GroupRecipe(45001, "Ingot Crafting", new String[]{"minecraft:iron_ingot_from_nuggets", "minecraft:iron_nugget", "minecraft:gold_ingot_from_nuggets", "minecraft:gold_nugget", "minecraft:furnace", "minecraft:blast_furnace"}));
            //put(45002, new GroupRecipe(45002, "Resource Blocks", new String[]{"minecraft:redstone_block", "minecraft:redstone", "minecraft:glowstone", "minecraft:iron_ingot_from_iron_block", "minecraft:iron_block", "minecraft:gold_block", "minecraft:gold_block", "minecraft:gold_ingot_from_gold_block", "minecraft:diamond", "minecraft:diamond_block", "minecraft:netherite_block", "minecraft:netherite_ingot_from_netherite_block", "minecraft:anvil"}));
            put(
                45003L, GroupRecipe(
                    45003, "brewing", arrayOf(
                        "minecraft:blaze_powder",
                        "minecraft:brewing_stand"
                    )
                )
            )
            put(
                45004L, GroupRecipe(
                    45004, "enchanting", arrayOf(
                        "minecraft:enchanting_table",
                        "minecraft:bookshelf"
                    )
                )
            )
            put(
                45005L, GroupRecipe(
                    45005, "bucket", arrayOf(
                        "minecraft:bucket"
                    )
                )
            )
            put(
                45006L, GroupRecipe(
                    45006, "flint_and_steel", arrayOf(
                        "minecraft:flint_and_steel"
                    )
                )
            )
            put(
                45007L, GroupRecipe(
                    45007, "beds", arrayOf(
                        "minecraft:black_bed",
                        "minecraft:blue_bed",
                        "minecraft:brown_bed",
                        "minecraft:cyan_bed",
                        "minecraft:gray_bed",
                        "minecraft:green_bed",
                        "minecraft:light_blue_bed",
                        "minecraft:light_gray_bed",
                        "minecraft:lime_bed",
                        "minecraft:magenta_bed",
                        "minecraft:orange_bed",
                        "minecraft:pink_bed",
                        "minecraft:purple_bed",
                        "minecraft:red_bed",
                        "minecraft:white_bed",
                        "minecraft:yellow_bed"
                    )
                )
            )
            put(
                45008L, GroupRecipe(
                    45008, "bottles", arrayOf(
                        "minecraft:glass_bottle"
                    )
                )
            )
            put(
                45009L, GroupRecipe(
                    45009, "shield", arrayOf(
                        "minecraft:shield"
                    )
                )
            )
            put(
                45010L, GroupRecipe(
                    45010, "fishing", arrayOf(
                        "minecraft:fishing_rod",
                        "minecraft:carrot_on_a_stick",
                        "minecraft:warped_fungus_on_a_stick"
                    )
                )
            )
            put(
                45011L, GroupRecipe(
                    45011, "campfires", arrayOf(
                        "minecraft:campfire",
                        "minecraft:soul_campfire"
                    )
                )
            )
            put(
                45044L, GroupRecipe(
                    45044, "spyglass", arrayOf(
                        "minecraft:spyglass"
                    )
                )
            )
            put(
                45045L, GroupRecipe(
                    45044, "lead", arrayOf(
                        "minecraft:lead"
                    )
                )
            )
        }
    }

    var progressiveRecipes: HashMap<Long, ProgressiveRecipe> = object : HashMap<Long, ProgressiveRecipe>() {
        init {
            put(
                45012L,
                ProgressiveRecipe(
                    45012, "progressive_weapons",
                    ArrayList(
                        listOf(
                            arrayOf(
                                "minecraft:stone_sword",
                                "minecraft:stone_axe"
                            ),
                            arrayOf(
                                "minecraft:iron_sword",
                                "minecraft:iron_axe"
                            ),
                            arrayOf(
                                "minecraft:diamond_sword",
                                "minecraft:diamond_axe"
                            )
                        )
                    )
                )
            )
            put(
                45013L,
                ProgressiveRecipe(
                    45013, "progressive_tools",
                    ArrayList(
                        listOf(
                            arrayOf(
                                "minecraft:stone_pickaxe",
                                "minecraft:stone_shovel",
                                "minecraft:stone_hoe"
                            ),
                            arrayOf(
                                "minecraft:iron_pickaxe",
                                "minecraft:iron_shovel",
                                "minecraft:iron_hoe"
                            ),
                            arrayOf(
                                "minecraft:diamond_pickaxe",
                                "minecraft:diamond_shovel",
                                "minecraft:diamond_hoe",
                                "minecraft:netherite_ingot"
                            )
                        )
                    )
                )
            )
            put(
                45014L,
                ProgressiveRecipe(
                    45013, "progressive_armor",
                    ArrayList(
                        listOf(
                            arrayOf(
                                "minecraft:iron_helmet",
                                "minecraft:iron_chestplate",
                                "minecraft:iron_leggings",
                                "minecraft:iron_boots"
                            ),
                            arrayOf(
                                "minecraft:diamond_helmet",
                                "minecraft:diamond_chestplate",
                                "minecraft:diamond_leggings",
                                "minecraft:diamond_boots"
                            )
                        )
                    )
                )
            )
            put(
                45001L,
                ProgressiveRecipe(
                    45001, "progressive_resource_crafting",
                    ArrayList(
                        listOf(
                            arrayOf(
                                "minecraft:iron_ingot_from_nuggets",
                                "minecraft:iron_nugget",
                                "minecraft:gold_ingot_from_nuggets",
                                "minecraft:gold_nugget",
                                "minecraft:furnace",
                                "minecraft:blast_furnace"
                            ),
                            arrayOf(
                                "minecraft:redstone",
                                "minecraft:redstone_block",
                                "minecraft:glowstone",
                                "minecraft:iron_ingot_from_iron_block",
                                "minecraft:iron_block",
                                "minecraft:gold_ingot_from_gold_block",
                                "minecraft:gold_block",
                                "minecraft:diamond",
                                "minecraft:diamond_block",
                                "minecraft:netherite_block",
                                "minecraft:netherite_ingot_from_netherite_block",
                                "minecraft:anvil",
                                "minecraft:emerald",
                                "minecraft:emerald_block",
                                "minecraft:copper_block"
                            )
                        )
                    )
                )
            )
        }
    }

    fun injectIRecipe(iRecipe: RecipeHolder<*>): Boolean {
        for (entry in recipes.entries) {
            for (namespaceID in entry.value.namespaceIDs) {
                LOGGER.trace("checking {} vs {},", iRecipe.id().toString(), namespaceID)
                if (iRecipe.id().toString() == namespaceID) {
                    entry.value.addIRecipe(iRecipe)
                    return true
                }
            }
        }
        for (entry in progressiveRecipes.entries) {
            var i = 0
            while (entry.value.namespaceIDs.size > i) {
                val namespaceIDs: Array<String> = entry.value.namespaceIDs[i]
                for (s in namespaceIDs) {
                    LOGGER.trace("checking {} vs {},", iRecipe.id().toString(), s)
                    if (iRecipe.id().toString() == s) {
                        entry.value.addIRecipe(iRecipe, i)
                        return true
                    }
                }
                ++i
            }
        }
        return false
    }

    fun hasID(id: Long): Boolean {
        return recipes.containsKey(id) || progressiveRecipes.containsKey(id)
    }

    fun getID(id: Long): APRecipe? {
        if (recipes.containsKey(id)) {
            return recipes[id]
        } else if (progressiveRecipes.containsKey(id)) {
            return progressiveRecipes[id]
        }
        return null
    }

    //our reset here is simple just reset what tier it thinks our progressive recipes are at.
    fun reset() {
        progressiveRecipes.forEach { (id: Long, recipe: ProgressiveRecipe) -> recipe.currentTier = 0 }
    }

    companion object {
        // Directly reference a log4j logger.
        private val LOGGER: Logger = LogManager.getLogger()
    }
}
