package me.xcue.hylib.lib.codecs

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec
import com.hypixel.hytale.codec.validation.Validators
import com.hypixel.hytale.math.vector.Location

enum class LocationCodec {
    /**
     * Codec for a full Location object
     */
    LOCATION(BuilderCodec.builder(Location::class.java) { Location() }
                .append(
                    KeyedCodec("World", Codec.STRING),
                    { loc, value -> loc.world = value },
                    { loc -> loc.world })
                .addValidator(Validators.nonNull())
                .add()
                .append(
                    KeyedCodec("X", Codec.DOUBLE),
                    { loc, value -> loc.position.x = value },
                    { loc -> loc.position.x })
                .addValidator(Validators.nonNull())
                .add()
                .append(
                    KeyedCodec("Y", Codec.DOUBLE),
                    { loc, value -> loc.position.y = value },
                    { loc -> loc.position.y })
                .addValidator(Validators.nonNull())
                .add()
                .append(
                    KeyedCodec("Z", Codec.DOUBLE),
                    { loc, value -> loc.position.z = value },
                    { loc -> loc.position.z })
                .addValidator(Validators.nonNull())
                .add()
                .append(
                    KeyedCodec("RotX", Codec.FLOAT),
                    { loc, value -> loc.rotation.x = value },
                    { loc -> loc.rotation.x })
                .add()
                .append(
                    KeyedCodec("RotY", Codec.FLOAT),
                    { loc, value -> loc.rotation.y = value },
                    { loc -> loc.rotation.y })
                .add()
                .append(
                    KeyedCodec("RotZ", Codec.FLOAT),
                    { loc, value -> loc.rotation.z = value },
                    { loc -> loc.rotation.z })
                .add()
                .build()
    );

    val builder: BuilderCodec<Location>

    constructor(builder: BuilderCodec<Location>) {
        this.builder = builder
    }
}