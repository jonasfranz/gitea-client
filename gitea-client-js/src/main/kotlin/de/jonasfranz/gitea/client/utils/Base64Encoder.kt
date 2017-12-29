package de.jonasfranz.gitea.client.utils

import kotlin.browser.window


actual class Base64Encoder actual constructor() {
    actual fun encodeToString(input: String): String {
        return window.btoa(input)
    }
}