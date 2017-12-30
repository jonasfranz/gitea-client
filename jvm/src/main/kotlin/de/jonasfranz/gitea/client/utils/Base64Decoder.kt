package de.jonasfranz.gitea.client.utils

import java.util.Base64

actual class Base64Decoder actual constructor() {
    private val decoder = Base64.getDecoder()
    actual fun decodeAsString(from: String): String {
        return decoder.decode(from).toString()
    }
}