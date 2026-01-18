package me.xcue.hylib.api.events

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.protocol.packets.interaction.SyncInteractionChain
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.hylib.lib.events.ISyncInteractionEvent

/**
 * Called when the player uses the interaction keybind ("F" by default)
 */
class PlayerUseEvent(ref: Ref<EntityStore?>, player: Player,
                     override val chain: SyncInteractionChain
): PlayerEvent<Void>(ref, player), ISyncInteractionEvent {}