package me.xcue.hylib.lib.services.packets

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.io.adapter.PacketAdapters
import com.hypixel.hytale.server.core.io.adapter.PacketWatcher

class PacketWatcherService(private val logger: HytaleLogger) {
    private val watchers = mutableListOf<PacketWatcher>()

    fun addWatcher(watcher: PacketWatcher) {
        watchers.add(watcher)
        logger.atInfo().log("Added ${watcher.javaClass}")
    }

    fun start() {
        watchers.forEach { PacketAdapters.registerInbound(it) }
        logger.atInfo().log("PacketWatcherService now listening...")
    }
}