package me.xcue.hylib.lib.services.storage

interface AbstractDataService<IdType, DataType> {
        fun load(id: IdType): DataType?
        fun save(data: DataType)
        fun loadAll(): List<DataType>
}