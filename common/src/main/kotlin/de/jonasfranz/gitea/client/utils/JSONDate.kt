package de.jonasfranz.gitea.client.utils

import kotlinx.serialization.KSerializer

expect class JSONDate() {
    fun getTime(): Number

    companion object : KSerializer<JSONDate>
}

val defaultDateFormat: String
    get() = "yyyy-MM-dd'T'HH:mm:ss'Z'"