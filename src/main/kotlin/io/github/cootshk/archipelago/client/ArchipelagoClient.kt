package io.github.cootshk.archipelago.client

import com.google.gson.Gson
import io.github.archipelagomw.flags.ItemsHandling
import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.managers.ConfigManager
import io.github.cootshk.archipelago.managers.EventManager
import io.github.cootshk.archipelago.managers.GoalManager
import io.github.cootshk.archipelago.managers.ItemManager
import io.github.archipelagomw.Client as APClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import org.apache.logging.log4j.LogManager
import org.lwjgl.glfw.GLFW
import java.lang.Exception
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.reflect.jvm.reflect

object ArchipelagoClient : ClientModInitializer, DedicatedServerModInitializer, APClient() {

    val path = Path("Archipelago")
    private val gson = Gson()
    private val logger = LogManager.getLogger()

    override fun onInitializeServer() = onInitializeClient()
    override fun onInitializeClient() { // treat this as the initializer because it's loaded when Fabric has set everything else up
        logger.info("Loading AP Client...")

        this.game = "minecraft"
        itemsHandlingFlags = ItemsHandling.SEND_ITEMS or ItemsHandling.SEND_OWN_ITEMS or ItemsHandling.SEND_STARTING_INVENTORY

        // Save Data
        if (!Files.exists(path)) {
            Files.createDirectories(path)
        }


        // Events
        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted)
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick)
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onServerStopped)
        ServerMessageEvents.CHAT_MESSAGE.register(this::onChatMessage)

        // debug, remove this
        val key = KeyMapping("key.archipelago.enable", GLFW.GLFW_KEY_N, KeyMapping.Category.MISC)
        val key2 = KeyMapping("key.archipelago.disable", GLFW.GLFW_KEY_M, KeyMapping.Category.MISC)
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (key.isDown) {
                GoalManager.bossesRequired = true
                ItemManager.updateLore()
            }
            if (key2.isDown) {
                GoalManager.bossesRequired = false
                ItemManager.updateLore()
            }
        }
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
        Archipelago.server = server
        setName(ConfigManager.playerName)
        connect()
    }
    private fun onServerTick(server: MinecraftServer) { // We are safe to ignore `server` here because it's set in onServerStarted and onServerStopped
        EventManager.runEvents()
    }
    private fun onServerStopped(server: MinecraftServer) {
        Archipelago.server = server
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
