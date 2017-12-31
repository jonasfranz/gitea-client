package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Serializable


/**
 * Represents an user
 * @param id user's id
 * @param username user's username
 * @param fullName user's full name
 * @param email user's email address
 * @param avatarURL URL to user's avatar
 */
@Serializable
data class User(
        var id: Int,
        @SerializedName("login")
        var username: String,
        @SerializedName("full_name")
        var fullName: String,
        var email: String,
        @SerializedName("avatar_url")
        var avatarURL: String
)