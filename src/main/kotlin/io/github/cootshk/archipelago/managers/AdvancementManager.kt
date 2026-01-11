package io.github.cootshk.archipelago.managers


import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.data.WorldData
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementProgress
import net.minecraft.resources.Identifier

object AdvancementManager {
    private val advancementData = object : HashMap<String, Long>() {
        init {
            // TODO: advancements
            // put("namespace:path", idL)
        }
    }

    val unreasonableAdvancements: MutableSet<Identifier> = object : HashSet<Identifier>() {
        init {
            // TODO: advancements
            // add(Identifier.parse("namespace/path"))
        }
    }

    private val earnedAdvancements: MutableSet<Long> = HashSet()

    fun getAdvancementID(namespacedID: String): Long {
        if (advancementData.containsKey(namespacedID)) return advancementData.get(namespacedID)!!
        return 0L
    }

    fun hasAdvancement(id: Long?): Boolean {
        return earnedAdvancements.contains(id)
    }

    fun hasAdvancement(namespacedID: String): Boolean {
        return earnedAdvancements.contains(getAdvancementID(namespacedID))
    }

    fun addAdvancement(id: Long) {
        earnedAdvancements.add(id)
//        APRandomizer.getAP().checkLocation(id)
        GoalManager.updateGoal(true)
        WorldData.getInstance().addLocation(id)
        syncAllAdvancements()
    }

    fun resendAdvancements() {
        for (earnedAdvancement in earnedAdvancements) {
//            APRandomizer.getAP().checkLocation(earnedAdvancement)
        }
    }

    fun syncAdvancement(advancementHolder: AdvancementHolder) {
        if (hasAdvancement(advancementHolder.id().toString())) {
            for (serverPlayerEntity in Archipelago.server.playerList.players) {
                val ap: AdvancementProgress = serverPlayerEntity.advancements.getOrStartProgress(advancementHolder)
                if (ap.isDone) continue
                for (remainingCriterion in ap.remainingCriteria) {
                    serverPlayerEntity.advancements.award(advancementHolder, remainingCriterion)
                }
            }
        }
        if (RecipeManager.hasReceived(advancementHolder.id)) {
            for (serverPlayerEntity in Archipelago.server.playerList.players) {
                val ap: AdvancementProgress = serverPlayerEntity.advancements.getOrStartProgress(advancementHolder)
                if (ap.isDone) continue
                for (remainingCriterion in ap.remainingCriteria) {
                    serverPlayerEntity.advancements.award(advancementHolder, remainingCriterion)
                }
            }
        }
    }

    fun syncAllAdvancements() {
        for (a in Archipelago.server.advancements.allAdvancements) {
            syncAdvancement(a)
        }
    }

    val finishedAmount: Int
        get() = earnedAdvancements.size

    fun setCheckedAdvancements(checkedLocations: MutableSet<Long>) {
        earnedAdvancements.addAll(checkedLocations)
        val data: WorldData = WorldData.getInstance()
        for (checkedLocation in checkedLocations) {
            data.addLocation(checkedLocation)
        }

        syncAllAdvancements()
    }

//    companion object {
        val hardAdvancements = object : HashSet<Identifier>() {
            init {
                add(Identifier.withDefaultNamespace("adventure/very_very_frightening")) // Very Very Frightening
                add(Identifier.withDefaultNamespace("nether/all_potions")) // A Furious Cocktail
                add(Identifier.withDefaultNamespace("husbandry/bred_all_animals")) // Two by Two
                add(Identifier.withDefaultNamespace("adventure/two_birds_one_arrow")) // Two Birds, One Arrow
                add(Identifier.withDefaultNamespace("adventure/arbalistic")) // Arbalistic
                add(Identifier.withDefaultNamespace("adventure/kill_all_mobs")) // Monsters Hunted
                add(Identifier.withDefaultNamespace("nether/create_full_beacon")) // Beaconator
                add(Identifier.withDefaultNamespace("husbandry/balanced_diet")) // A Balanced Diet
                add(Identifier.withDefaultNamespace("nether/uneasy_alliance")) // Uneasy Alliance
                add(Identifier.withDefaultNamespace("nether/netherite_armor")) // Cover Me in Debris
                add(Identifier.withDefaultNamespace("husbandry/complete_catalogue")) // A Complete Catalogue
                add(Identifier.withDefaultNamespace("adventure/lightning_rod_with_villager_no_fire")) // Surge Protector
                add(Identifier.withDefaultNamespace("adventure/play_jukebox_in_meadows")) // Sound of Music
                add(Identifier.withDefaultNamespace("adventure/trade_at_world_height")) // Star Trader
                add(Identifier.withDefaultNamespace("husbandry/leash_all_frog_variants")) // When the Squad Hops into Town
                add(Identifier.withDefaultNamespace("husbandry/leash_all_frog_variants")) // With Our Powers Combined!
                add(Identifier.withDefaultNamespace("husbandry/froglights")) // With Our Powers Combined!
            }
        }
//    }
}