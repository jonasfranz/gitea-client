package de.jonasfranz.gitea.client.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
expect class JSONDate() {
    fun getTime(): Number

    @Serializer(forClass = JSONDate::class)
    companion object : KSerializer<JSONDate>
}

val defaultDateFormat: String
    get() = "yyyy-MM-dd'T'HH:mm:ss'Z'"