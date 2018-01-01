package de.jonasfranz.gitea.client

import de.jonasfranz.gitea.client.models.AccessToken
import de.jonasfranz.gitea.client.requests.*

expect class Client(url: String, accessToken: String? = null) {
    var applicationName: String
    var accessToken: String?
    var url: String

    suspend fun sendRequest(request: Request): Response
}

@Suppress("FunctionName")
suspend fun Client(url: String, username: String, password: String): Client = Client(url).apply {
    // Retrieving existing tokens for [applicationName]
    accessToken = access.listAccessTokens(AccessAPI.createBasicAuth(username, password), username).filter {
        it.name == applicationName
    }.firstOrNull()?.sha1
    if (accessToken == null) {
        // Create new access token
        accessToken = access.createAccessToken(AccessAPI.createBasicAuth(username, password), username, AccessToken.CreateAccessTokenOption(applicationName)).sha1
    }
}

val Client.access
    get() = AccessAPI(this)

val Client.users
    get() = UsersAPI(this)

val Client.repositories
    get() = RepositoriesAPI(this)