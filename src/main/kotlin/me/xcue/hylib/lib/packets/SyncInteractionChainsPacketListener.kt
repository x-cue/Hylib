package me.xcue.hylib.lib.packets

import com.hypixel.hytale.event.IEvent
import com.hypixel.hytale.protocol.InteractionType
import com.hypixel.hytale.protocol.Packet
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains
import com.hypixel.hytale.server.core.HytaleServer
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.io.PacketHandler
import com.hypixel.hytale.server.core.io.adapter.PacketWatcher
import com.hypixel.hytale.server.core.universe.Universe
import me.xcue.hylib.api.events.PlayerLeftClickEvent
import me.xcue.hylib.api.events.PlayerRightClickEvent
import me.xcue.hylib.api.events.PlayerUseEvent

class SyncInteractionChainsPacketListener : PacketWatcher {
    override fun accept(h: PacketHandler?, p: Packet?) {
        if (p == null || p.id != 290 || h == null) return
        val chains = p as SyncInteractionChains
        val updates = chains.updates
        val auth = h.auth ?: return
        val pRef = Universe.get().getPlayer(auth.uuid) ?: return
        val world = pRef.worldUuid?.let { Universe.get().getWorld(it) } ?: return

        world.execute {
            val storeRef = pRef.reference ?: return@execute
            val player = storeRef.store.getComponent(storeRef, Player.getComponentType()) ?: return@execute
            val heldItem = player.inventory.itemInHand

            for (item in updates) {
                when (item.interactionType) {
                    InteractionType.Use -> HytaleServer.get().eventBus.dispatchFor(PlayerUseEvent::class.java).dispatch(PlayerUseEvent(storeRef, player))
                    InteractionType.Primary -> HytaleServer.get().eventBus.dispatchFor(PlayerLeftClickEvent::class.java).dispatch(PlayerLeftClickEvent(storeRef, player, heldItem))
                    InteractionType.Secondary -> HytaleServer.get().eventBus.dispatchFor(PlayerRightClickEvent::class.java).dispatch(PlayerRightClickEvent(storeRef, player, heldItem))
                    else -> continue
                }
            }
        }
    }
}