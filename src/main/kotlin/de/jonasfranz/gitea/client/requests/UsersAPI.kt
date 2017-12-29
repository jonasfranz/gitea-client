package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.models.User

expect class UsersAPI : API {
    suspend fun getUserInfo(username: String): User
}