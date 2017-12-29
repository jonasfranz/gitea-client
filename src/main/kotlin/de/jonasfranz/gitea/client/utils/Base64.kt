package de.jonasfranz.gitea.client.utils

object Base64 {
    val Encoder = Base64Encoder()
    val Decoder = Base64Decoder()
}

expect class Base64Encoder() {
    fun encodeToString(input: String): String
}

expect class Base64Decoder() {
    fun decodeAsString(from: String): String
}