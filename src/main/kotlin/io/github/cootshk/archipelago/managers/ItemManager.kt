package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.impl.ItemStackMixin

object ItemManager {
    fun updateLore() {
        @Suppress("CAST_NEVER_SUCCEEDS") // it does though
        Archipelago.player.inventory.forEach { (it as Any as ItemStackMixin).`archipelago$updateLore`()  }
    }
}