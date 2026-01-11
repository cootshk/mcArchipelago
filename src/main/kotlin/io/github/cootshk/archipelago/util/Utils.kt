package io.github.cootshk.archipelago.util

import net.minecraft.world.phys.Vec3
import kotlin.math.cos
import kotlin.math.sin


/// https://github.com/KonoTyran/Minecraft_AP_Randomizer/blob/master/src/main/java/gg/archipelago/aprandomizer/common/Utils/Utils.java
object Utils {
    fun getRandomPosition(pos: Vec3, radius: Int): Vec3 {
        val a = Math.random() * Math.PI * 2
        val b = Math.random() * Math.PI / 2
        val x = radius * cos(a) * sin(b) + pos.x
        val z = radius * sin(a) * sin(b) + pos.z
        val y = radius * cos(b) + pos.y
        return Vec3(x, y, z)
    }
}