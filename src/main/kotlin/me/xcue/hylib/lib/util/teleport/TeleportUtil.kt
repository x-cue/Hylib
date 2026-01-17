package me.xcue.hylib.lib.util.teleport

import com.hypixel.hytale.math.vector.Location
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe

object TeleportUtil {
    /**
     * Teleport a player to the target location
     */
    fun teleport(pRef: PlayerRef, loc: Location) {
        val world = Universe.get().getWorld(loc.world) ?: return

        val ref = pRef.reference ?: return

        ref.store.addComponent(
            ref, Teleport.getComponentType(), Teleport(world, loc.position.clone(), loc.rotation.clone())
        )
    }

    /**
     * Teleport a player to the target player
     */
    fun teleport(pRef: PlayerRef, tPRef: PlayerRef) {
        val tLoc = Location(tPRef.transform.clone())

        teleport(pRef, tLoc)
    }
}