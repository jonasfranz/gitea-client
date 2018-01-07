package de.jonasfranz.gitea.client.models

import kotlinx.serialization.Serializable

@Serializable
data class Label(
        var id: Int,
        var name: String,
        var color: String,
        var url: String
)