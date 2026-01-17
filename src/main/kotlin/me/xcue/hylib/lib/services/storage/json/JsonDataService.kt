package me.xcue.hylib.lib.services.storage.json

import com.google.gson.Gson
import com.google.gson.JsonIOException
import me.xcue.hylib.Hylib
import me.xcue.hylib.lib.services.storage.AbstractDataService
import me.xcue.hylib.lib.services.storage.Identifiable
import java.io.File

class JsonDataService<IdType, DataType : Identifiable<IdType>>(
    private val dataClass: Class<DataType>,
    parentDir: File,
    subDir: String,
    private val gson: Gson = Gson()
) : AbstractDataService<IdType, DataType> {
    private val dataDir: File =
        File(parentDir, subDir).apply { require(mkdirs() || exists()) { "Failed to create data dir: $this" } }

    /**
     * Load a specific instance of data from its json file
     */
    @Throws(JsonIOException::class)
    override fun load(id: IdType): DataType? {
        val file = File(dataDir, "$id.json")
        if (!file.exists()) return null

        return file.reader().use { gson.fromJson(it, dataClass) }
    }

    /**
     * Save a specific instance of data to its json file
     */
    @Throws(JsonIOException::class)
    override fun save(data: DataType) {
        val file = File(dataDir, "${data.id}.json")

        file.writer().use { gson.toJson(data, it) }
    }

    /**
     * Load all the data stored
     */
    override fun loadAll(): List<DataType> {
        val data: MutableList<DataType> = mutableListOf()

        dataDir.listFiles { _, name ->
            name.endsWith(".json")
        }?.forEach { file ->
            try {
                file.reader().use { data.add(gson.fromJson(it, dataClass)) }
            } catch (e: Exception) {
                Hylib.Companion.LOGGER.atWarning().log("Failed to load ${file.name}: ${e.message}")
            }
        }

        return data
    }
}