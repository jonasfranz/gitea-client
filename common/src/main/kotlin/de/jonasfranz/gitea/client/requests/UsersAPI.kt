package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.User

class UsersAPI(client: Client) : API(client) {
    suspend fun getUserInfo(username: String): User = TODO()
}