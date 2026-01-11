package io.github.cootshk.archipelago.event.traps

import io.github.cootshk.archipelago.event.Event
import net.minecraft.server.level.ServerPlayer

interface Trap: Event {
    var enabled: Boolean
    fun trigger(player: ServerPlayer)
    override fun onLoad(player: ServerPlayer) {
        // TODO: Tell player they got trapped in chat
        this.trigger(player)
    }
}