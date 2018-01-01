package de.jonasfranz.gitea.client.models

import kotlinx.serialization.Serializable

@Serializable
data class Branch(
        var name: String
        //TODO Add commit-payload
)