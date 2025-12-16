package io.github.cootshk.archipelago.managers

import com.mojang.authlib.minecraft.client.MinecraftClient
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft

object ConfigManager {
    // TODO:
    /*
    Wait for Owo-lib to update to 1.21.11.
    Use its config system.

    YAML generation in-game?
     */

    // For now, these are stubs for compilation.
    var serverAddress: String = "localhost"
    var serverPort: Int = 38281
    private var _playerName: String? = null
    var playerName: String
        get() = _playerName ?: Minecraft.getInstance().user.name
        set(value) { _playerName = (value.ifBlank { null }) }
}