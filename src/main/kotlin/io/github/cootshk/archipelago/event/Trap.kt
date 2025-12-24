package io.github.cootshk.archipelago.event

import net.minecraft.server.level.ServerPlayer

interface Trap {
    var enabled: Boolean
    fun trigger(player: ServerPlayer)
}