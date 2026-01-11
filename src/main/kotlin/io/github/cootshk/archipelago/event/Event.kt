package io.github.cootshk.archipelago.event

import net.minecraft.server.level.ServerPlayer

interface Event {
    fun onLoad(player: ServerPlayer)
}