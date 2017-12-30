package de.jonasfranz.gitea.client.utils

import kotlin.browser.window

actual class Base64Decoder actual constructor() {
    actual fun decodeAsString(from: String): String {
        return window.atob(from)
    }
}