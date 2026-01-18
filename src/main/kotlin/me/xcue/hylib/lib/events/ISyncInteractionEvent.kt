package me.xcue.hylib.lib.events

import com.hypixel.hytale.protocol.InteractionType
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain

interface ISyncInteractionEvent {
    val chain: SyncInteractionChain
}