package me.xcue.hylib.lib.arguments

import com.hypixel.hytale.server.core.command.system.ParseResult
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType

class StringArgumentType: SingleArgumentType<String> {
    /**
     * Abstract constructor for self-contained arguments that won't require further processing
     */
    constructor(name: String, usage: String, examples: String) : super(name, usage, examples) {

    }

    /**
     * Primitive constructor to use with ProcessingArgumentTypes
     */
    constructor() : super("", "", "")

    override fun parse(
        arg: String?,
        result: ParseResult?
    ): String? {
        return arg
    }
}