package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.event.Event
import io.github.cootshk.archipelago.event.StopEvent
import io.github.cootshk.archipelago.event.TickingEvent

object EventManager {
    @JvmStatic
    val runningEvents = mutableListOf<() -> Unit>()
    @JvmStatic
    fun triggerEvent(event: Event) {
        event.onLoad(Archipelago.player)
        val e = event as? TickingEvent
        if (e != null) {
            runningEvents.add(e::onTick)
        }
    }
    @JvmStatic
    fun runEvents() {
        runningEvents.map { event ->
            try { event() }
            catch (_: StopEvent) {
                assert(runningEvents.remove(event)) { "Unable to remove event?" }
            }
        }
    }
}