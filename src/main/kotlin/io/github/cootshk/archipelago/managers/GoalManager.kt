package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.types.Boss

object GoalManager {
    // DEBUG, remove this
    var bossesRequired = true
    @JvmStatic
    fun isBossRequired(boss: Boss): Boolean {
//        TODO("Not yet implemented")
        return bossesRequired
    }

    fun updateGoal(something: Boolean) {
        TODO("Not yet implemented")
    }
}