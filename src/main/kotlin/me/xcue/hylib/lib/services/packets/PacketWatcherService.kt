package me.xcue.hylib.lib.services.packets

import com.hypixel.hytale.server.core.io.adapter.PacketAdapters
import com.hypixel.hytale.server.core.io.adapter.PacketWatcher

class PacketWatcherService {
    private val watchers = mutableListOf<PacketWatcher>()

    fun addWatcher(watcher: PacketWatcher) {
        watchers.add(watcher)
    }

    fun start() {
        watchers.forEach { PacketAdapters.registerInbound(it) }
    }
}