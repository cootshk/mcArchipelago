package io.github.cootshk.archipelago.client

import io.github.cootshk.archipelago.managers.ConfigManager
import io.github.archipelagomw.Client as APClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.chat.LoggedChatEvent
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import org.apache.logging.log4j.LogManager
import java.lang.Exception

object ArchipelagoClient : ClientModInitializer, DedicatedServerModInitializer, APClient() {

    private val logger = LogManager.getLogger()
    override fun onInitializeServer() = onInitializeClient()
    override fun onInitializeClient() {
        logger.info("Loading AP Client...")
        this.game = "minecraft"


        // Events
        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted)
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onServerStopped)
        ServerMessageEvents.CHAT_MESSAGE.register(this::onChatMessage)
    }

    override fun onError(ex: Exception) {
        logger.error(ex)
        throw ex
    }

    override fun onClose(reason: String, attemptingReconnect: Int) {
        TODO("Not yet implemented")
    }

    // Events
    private fun onServerStarted(server: MinecraftServer) {
        setName(ConfigManager.playerName)
        connect()
    }
    private fun onServerStopped(server: MinecraftServer) {
        disconnect()
    }
    private fun onChatMessage(message: PlayerChatMessage, player: ServerPlayer, bound: ChatType.Bound) {
        if (player.uuid == Minecraft.getInstance().player?.uuid) {
            this.sendChat(message.signedContent())
        }
    }

    // Socket
    private fun connect() {
        logger.info("Connecting to Archipelago server...")
        connect(ConfigManager.serverAddress +":"+ ConfigManager.serverPort)
        TODO("config screen for connection settings")
    }
}
