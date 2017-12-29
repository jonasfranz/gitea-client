package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.User
import io.ktor.client.request

actual class UsersAPI(client: Client) : API(client) {
    actual suspend fun getUserInfo(username: String): User = client.ktor.request {
        auth()

    }

}