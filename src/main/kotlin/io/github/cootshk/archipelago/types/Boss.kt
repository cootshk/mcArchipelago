package io.github.cootshk.archipelago.types

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

enum class Boss {
    DRAGON,
    WITHER,
    WARDEN;

    companion object {
        @JvmStatic
        fun fromEntity(entity: Entity): Boss? {
            return fromEntity(entity.type)
        }
        @JvmStatic
        fun <T : Entity> fromEntity(entity: EntityType<T>): Boss? {
            return when (entity) {
                EntityType.ENDER_DRAGON -> DRAGON
                EntityType.WITHER -> WITHER
                EntityType.WARDEN -> WARDEN
                else -> null
            }
        }
        @JvmStatic
        fun fromString(value: String): Boss? {
            return when (value.uppercase()) {
                "DRAGON" -> DRAGON
                "WITHER" -> WITHER
                "WARDEN" -> WARDEN
                else -> null
            }
        }
    }
}