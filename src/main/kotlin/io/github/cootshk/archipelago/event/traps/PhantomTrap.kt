package io.github.cootshk.archipelago.event.traps

import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.event.TickingEvent
import io.github.cootshk.archipelago.util.Utils
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySpawnReason
import org.apache.logging.log4j.LogManager
import kotlin.random.Random

class PhantomTrap : Trap, TickingEvent {
    private companion object {
        private var _enabled = true; // CHANGE
        private val logger = LogManager.getLogger()
        val maxTime: Int get() = 45 * 20 // 45s
    }
    override var enabled = _enabled
    var timer = maxTime
        private set
    var phantomList = mutableListOf<Entity>()
    lateinit var player: ServerPlayer
    override fun trigger(player: ServerPlayer) {
        this.player = player
        Archipelago.server.execute {
            val world = player.level()
            val phantomCount = Random.nextInt(3, 5) // TODO: make this a YAML option
            for (i in 0..phantomCount) {
                val phantom = EntityType.PHANTOM.create(world, EntitySpawnReason.COMMAND)
                phantom ?: continue
                phantom.isInvulnerable = true
                phantom.addEffect(MobEffectInstance(
                    MobEffects.FIRE_RESISTANCE,
                    MobEffectInstance.INFINITE_DURATION,
                    MobEffectInstance.MAX_AMPLIFIER,
                    false, true, false))
                Utils.getRandomPosition(player.position(), 5)
                if (world.addFreshEntity(phantom)) {
                    phantomList.add(phantom)
                } else {
                    logger.warn("Failed to add Phantom #$i")
                }
            }
        }
    }
    override fun onTick() {
        if (--timer < 0) {
            phantomList.map { phantom ->
                phantom.kill(player.level())
            }
            finish()
        }
    }
}