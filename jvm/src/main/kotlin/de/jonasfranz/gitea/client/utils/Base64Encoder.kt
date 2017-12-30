package de.jonasfranz.gitea.client.utils

import java.util.Base64

actual class Base64Encoder actual constructor() {
    private val encoder = Base64.getEncoder()
    actual fun encodeToString(input: String): String {
        return encoder.encodeToString(input.toByteArray())
    }
}