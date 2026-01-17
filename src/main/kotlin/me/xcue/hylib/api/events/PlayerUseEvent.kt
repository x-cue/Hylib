package me.xcue.hylib.api.events

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore

/**
 * Called when the player uses the interaction keybind ("F" by default)
 */
class PlayerUseEvent(ref: Ref<EntityStore?>, player: Player): PlayerEvent<Void>(ref, player) {}