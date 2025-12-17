package io.github.cootshk.archipelago.managers

import io.github.cootshk.archipelago.data.SerializableData
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import kotlin.io.path.Path
import kotlin.io.path.exists

object DataManager {
    private val dataPath = Path("archipelago")
    init {
        if (!dataPath.exists()) {
            Files.createDirectories(dataPath)
        }
    }
    var data: SerializableData? = null

    fun dumpData(filename: String) {
        Json.encodeToString(data)
            .also { jsonString ->
                Files.writeString(
                    dataPath.resolve(filename),
                    jsonString,
                    StandardOpenOption.CREATE
                )
            }
    }
    fun loadData(filename: String) {
        val file = dataPath.resolve(filename)
        if (file.exists()) {
            val jsonString = Files.readString(file)
            data = Json.decodeFromString<SerializableData>(jsonString)
        } else data = null
    }
}