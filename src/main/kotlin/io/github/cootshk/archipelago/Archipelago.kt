package io.github.cootshk.archipelago

import net.fabricmc.api.ModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.client.server.IntegratedServer
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import org.apache.logging.log4j.LogManager

object Archipelago : ModInitializer {
    const val MODID = "archipelago"
    private val LOGGER = LogManager.getLogger()
    override fun onInitialize() {
        LOGGER.info("Loading Archipelago!")
    }

    // Spaghetti
    val player: ServerPlayer get() = (Minecraft.getInstance().player ?: server.playerList.getPlayer(Minecraft.getInstance().user.name)!!) as ServerPlayer
    private var _server: MinecraftServer? = null
    var server: MinecraftServer
        get() {
            if (_server != null) return _server!!
            if (!Minecraft.getInstance().isSingleplayer || !Minecraft.getInstance().hasSingleplayerServer()) {
                LOGGER.error("RecipeManager initialized outside of singleplayer context!")
                throw IllegalStateException("RecipeManager can only be initialized in singleplayer context!")
            }
            return Minecraft.getInstance().singleplayerServer!!
        }
        set(value) {_server = value}
}
