package me.xcue.hylib.api.events

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore

class PlayerRightClickEvent(ref: Ref<EntityStore?>, player: Player, val item: ItemStack?): PlayerEvent<Void>(ref, player) {}