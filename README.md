# Hylib – Hytale Utility Library

**Hylib** is a lightweight utility library for Kotlin-based Hytale plugins. It provides event handling, storage helpers, codecs, and utility functions to make plugin development smoother.

It is still usable with Java plugins, but some class names may end in `Kt`.

[CurseForge page / download](https://legacy.curseforge.com/hytale/mods/hylib)

---

## Included Files / Packages

Hylib currently provides the following classes and utilities:

| File                                     | Description                                                                                                                                                                            |
|------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `PlayerLeftClickEvent.kt`                | Event triggered when a player left-clicks.                                                                                                                                             |
| `PlayerRightClickEvent.kt`               | Event triggered when a player right-clicks.                                                                                                                                            |
| `PlayerUseEvent.kt`                      | Event triggered when a player emits the "Use" keybind (F by default).                                                                                                                  |
| `StringArgumentType.kt`                  | Utility for parsing string command arguments.                                                                                                                                          |
| `LocationCodec.kt`                       | Codec for serializing/deserializing Hytale locations.                                                                                                                                  |
| `SyncInteractionChainsPacketListener.kt` | Internal listener for player interaction packets. Do not interact directly.                                                                                                            |
| `PacketWatcherService.kt`                | Internal service for managing packet watchers. Do not interact directly.                                                                                                               |
| `DateTimeUtil.kt`                        | Utility functions for handling dates and times.                                                                                                                                        |
| `TeleportUtil.kt`                        | Helper functions for teleporting players.                                                                                                                                              |
| `Hylib.kt`                               | Main entry point / central library object.                                                                                                                                             |
| Storage & JSON Utilities                 | Hylib’s storage and identifiable system works together internally. `JsonStorage` is one implementation. Users generally do not need to interact directly with the packets or watchers. |

---

## Getting Started

> Hylib is best used as a server-side mod. Shading it into other plugins may break internal state required by some functions and future utilities.

### 1. Add Hylib as a dependency in your plugin

Hylib is now **available on Maven Central**. You can add it directly as a Gradle dependency:

```kotlin
repositories {
    mavenCentral()    
}

dependencies {
    comipileOnly("me.xcue:hylib:26.0.0")
}
```

Additionally, make sure to include it in your `manifest.json` in the `resources` folder:

```json
{
  "Dependencies": {
    "xcue:Hylib": "^26.0.0"
  }
}
```

No manual `.jar` downloads are required — Gradle will automatically fetch it from Maven Central.

---

### 2. Example Usage

Hylib provides events, utilities, and storage helpers. For example, listening to the "Use" keybind:

```kotlin
import me.xcue.hylib.api.events.PlayerUseEvent

// Register event listener
eventRegistry.register(PlayerUseEvent::class.java) { e ->
println("Player pressed Use: ${e.playerRef}")
}
```

Other utilities like `TeleportUtil` and `DateTimeUtil` can be used directly as needed.

> ⚠️ Internal packet listeners and watchers (`SyncInteractionChainsPacketListener`, `PacketWatcherService`) are handled automatically. **Do not interact with them directly.**

---

### Recommended Use

- Ideal for **Kotlin Hytale plugin development**.
- Includes **event handling**, **storage utilities**, **teleport helpers**, **date/time helpers**, and **codec utilities**.
- Lightweight and designed to integrate smoothly with other plugins or your own Hylib-based projects.
