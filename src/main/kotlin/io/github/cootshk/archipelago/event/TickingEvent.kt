package io.github.cootshk.archipelago.event

interface TickingEvent: Event {
    fun onTick()
    fun finish() { throw StopEvent() }
}