package me.xcue.hylib

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import me.xcue.hylib.lib.packets.SyncInteractionChainsPacketListener
import me.xcue.hylib.lib.services.packets.PacketWatcherService

/* This is the main class: the entry point for your plugin.
 * Use the setup function to register commands or event listeners.
 */

class Hylib(init: JavaPluginInit) : JavaPlugin(init) {
    companion object {
        val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
        private val packetService = PacketWatcherService()
    }

    init {
        LOGGER.atInfo().log("Hello from ${this.name} version ${this.manifest.version}")
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin ${this.name}")

        startPacketService()
    }

    private fun startPacketService() {
        LOGGER.atInfo().log("Starting PacketWatcherService")

        // Add all watchers
        packetService.addWatcher(SyncInteractionChainsPacketListener())

        LOGGER.atInfo().log("PacketWatcherService now listening...")
    }
}

