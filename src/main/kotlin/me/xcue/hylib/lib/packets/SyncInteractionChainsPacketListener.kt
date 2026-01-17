package me.xcue.hylib.lib.packets

import com.hypixel.hytale.protocol.InteractionType
import com.hypixel.hytale.protocol.Packet
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChains
import com.hypixel.hytale.server.core.HytaleServer
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.io.PacketHandler
import com.hypixel.hytale.server.core.io.adapter.PacketWatcher
import com.hypixel.hytale.server.core.universe.Universe
import me.xcue.hylib.api.events.PlayerLeftClickEvent
import me.xcue.hylib.api.events.PlayerRightClickEvent
import me.xcue.hylib.api.events.PlayerUseEvent

class SyncInteractionChainsPacketListener: PacketWatcher {
    private val bus = HytaleServer.get().eventBus
    private val useEventDispatcher = bus.dispatchFor(PlayerUseEvent::class.java)
    private val rightClickEventDispatcher = bus.dispatchFor(PlayerRightClickEvent::class.java)
    private val leftClickEventDispatcher = bus.dispatchFor(PlayerLeftClickEvent::class.java)

    override fun accept(h: PacketHandler?, p: Packet?) {
        if (p == null || p.id != 290 || h == null) return
        val chains = p as SyncInteractionChains
        val updates = chains.updates
        val auth = h.auth ?: return
        val pRef = Universe.get().getPlayer(auth.uuid) ?: return
        val storeRef = pRef.reference ?: return
        val player = storeRef.store.getComponent(storeRef, Player.getComponentType()) ?: return
        val heldItem = player.inventory.itemInHand

        for (item in updates) {
            when (item.interactionType) {
                InteractionType.Use -> useEventDispatcher.dispatch(PlayerUseEvent(storeRef, player))
                InteractionType.Primary -> leftClickEventDispatcher.dispatch(PlayerLeftClickEvent(storeRef, player, heldItem))
                InteractionType.Secondary -> rightClickEventDispatcher.dispatch(PlayerRightClickEvent(storeRef, player, heldItem))
                else -> continue
            }
        }
    }
}