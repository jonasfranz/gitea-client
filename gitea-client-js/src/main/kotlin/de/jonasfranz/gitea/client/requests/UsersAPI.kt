package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.User

actual class UsersAPI(client: Client) : API(client) {
    actual suspend fun getUserInfo(username: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}