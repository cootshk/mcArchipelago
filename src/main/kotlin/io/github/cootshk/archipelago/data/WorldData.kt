package io.github.cootshk.archipelago.data

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import io.github.cootshk.archipelago.Archipelago
import io.github.cootshk.archipelago.util.SavedDataTypeFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.saveddata.SavedData
import net.minecraft.world.level.saveddata.SavedDataType
import org.apache.commons.lang3.ArrayUtils
import java.util.Arrays
import java.util.function.Supplier


@Serializable
class WorldData : SavedData {

    constructor()

    var seedName = ""
        set(name) {
            field = name
            this.setDirty()
        }
    var dragonState: BossState
        get() = BossState.fromStateID(_dragonState)
        set(state) {
            _dragonState = state.getStateID()
        }
    private var _dragonState: Int = BossState.ASLEEP.getStateID()
        set(value) {
            field = value
            this.setDirty()
        }
    var witherState: BossState
        get() = BossState.fromStateID(_witherState)
        set(state) {
            _witherState = state.getStateID()
        }
    private var _witherState: Int = BossState.ASLEEP.getStateID()
        set(value) {
            field = value
            this.setDirty()
        }
    var jailPlayers = true
        set(value) {
            field = value
            this.setDirty()
        }
    var locations: MutableSet<Long> = mutableSetOf()
        private set
    private var index: Long = 0L
    var playerIndex: MutableMap<String, Int> = hashMapOf()
        private set

    fun addLocation(location: Long) {
        this.locations.add(location)
        this.setDirty()
    }

    fun addLocations(locations: Array<Long>) {
        this.locations.addAll(Lists.newArrayList<Long>(Arrays.stream(locations).iterator()))
        this.setDirty()
    }

    fun updatePlayerIndex(playerUUID: String, index: Int) {
        playerIndex[playerUUID] = index
        this.setDirty()
    }

    fun getPlayerIndex(playerUUID: String): Int {
        return playerIndex.getOrDefault(playerUUID, 0)
    }

    var itemIndex: Long
        get() = this.index
        set(index) {
            this.index = index
            this.setDirty()
        }

//    public override fun save(compoundTag: CompoundTag): CompoundTag {
//        compoundTag.putString("seedName", seedName)
//        compoundTag.putInt("dragonState", dragonState)
//        compoundTag.putBoolean("jailPlayers", jailPlayers)
//        compoundTag.putLongArray("locations", locations.stream().toList().toLongArray())
//        compoundTag.putLong("index", index)
//        val tagIndex = CompoundTag()
//        this.playerIndex.forEach { (string: String?, l: Int?) -> tagIndex.putLong(string!!, l) }
//        compoundTag.put("playerIndex", tagIndex)
//        return compoundTag
//    }
    private constructor(
        seedName: String,
        dragonState: Int,
        witherState: Int,
        jailPlayers: Boolean,
        locations: LongArray,
        playerIndex: MutableMap<String, Int>,
        itemIndex: Long
    ) : this(
        seedName,
        BossState.fromStateID(dragonState),
        BossState.fromStateID(witherState),
        jailPlayers,
        locations,
        playerIndex,
        itemIndex
    )

    private constructor(
        seedName: String,
        dragonState: BossState,
        witherState: BossState,
        jailPlayers: Boolean,
        locations: LongArray,
        playerIndex: MutableMap<String, Int>,
        itemIndex: Long
    ) {
        this.seedName = seedName
        this.dragonState = dragonState
        this.witherState = witherState
        this.jailPlayers = jailPlayers
        this.locations = HashSet<Long>(mutableSetOf(*ArrayUtils.toObject(locations)))
        this.index = itemIndex
        this.playerIndex = playerIndex
    }

    companion object {
        private val CODEC: Codec<WorldData> = Codec.STRING.xmap(
            WorldData::new,
            Json::encodeToString
        )

        val TYPE: SavedDataType<WorldData> = SavedDataTypeFactory.build(
            "ap_world_data",  // The unique name for this saved data.
            Supplier { WorldData() },  // If there's no 'WorldData', yet create one and refresh fields.
            CODEC,  // The codec used for serialization/deserialization.
            null // A data fixer, which is not needed here.
        )

        enum class BossState {
            KILLED,
            SPAWNED,
            WAITING,
            ASLEEP;
            fun getStateID(): Int {
                return when (this) {
                    KILLED -> 30
                    SPAWNED -> 20
                    WAITING -> 15
                    ASLEEP -> 10
                }
            }
            companion object {
                fun fromStateID(id: Int): BossState {
                    return when (id) {
                        30 -> KILLED
                        20 -> SPAWNED
                        15 -> WAITING
                        10 -> ASLEEP
                        else -> throw IllegalArgumentException("Invalid state id: $id")
                    }
                }
            }
        }

        private fun new(data: String): WorldData {
            return Json.decodeFromString<WorldData>(data)
        }
        fun getInstance(): WorldData {
            return Archipelago.server.getLevel(ServerLevel.OVERWORLD)?.dataStorage?.computeIfAbsent(TYPE) ?: WorldData()
        }
    }
}