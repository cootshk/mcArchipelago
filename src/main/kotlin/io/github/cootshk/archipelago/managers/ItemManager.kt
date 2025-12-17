package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.impl.ItemStackMixin
import net.minecraft.client.Minecraft

object ItemManager {
    fun updateLore() {
        @Suppress("CAST_NEVER_SUCCEEDS") // it does though
        Minecraft.getInstance().player?.inventory?.forEach { (it as Any as ItemStackMixin).`archipelago$updateLore`()  }
    }
}