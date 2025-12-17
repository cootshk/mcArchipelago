package io.github.cootshk.archipelago

import net.fabricmc.api.ModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.client.server.IntegratedServer
import org.apache.logging.log4j.LogManager

object Archipelago : ModInitializer {
    const val MODID = "archipelago"
    private val LOGGER = LogManager.getLogger()
    override fun onInitialize() {
    }
    val server: IntegratedServer
        get() {
            if (!Minecraft.getInstance().isSingleplayer || !Minecraft.getInstance().hasSingleplayerServer()) {
                LOGGER.error("RecipeManager initialized outside of singleplayer context!")
                throw IllegalStateException("RecipeManager can only be initialized in singleplayer context!")
            }
            return Minecraft.getInstance().singleplayerServer!!
        }
}
