package de.jonasfranz.gitea.client.models

import kotlinx.serialization.Serializable

@Serializable
data class AccessToken(var name: String, var sha1: String) {
    @Serializable
    data class CreateAccessTokenOption(var name: String)
}