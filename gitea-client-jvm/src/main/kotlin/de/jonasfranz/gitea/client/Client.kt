package de.jonasfranz.gitea.client

import de.jonasfranz.gitea.client.models.AccessToken
import de.jonasfranz.gitea.client.requests.AccessAPI
import de.jonasfranz.gitea.client.requests.createBasicAuth
import io.ktor.client.HttpClient
import io.ktor.client.backend.HttpClientBackendFactory
import io.ktor.client.backend.jetty.JettyHttp2Backend
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.experimental.async

actual class Client actual constructor(var url: String, var accessToken: String?) {
    val engineFactory: HttpClientBackendFactory = { JettyHttp2Backend() }
    actual var applicationName: String = "kt-client"

    var access = AccessAPI(this)

    actual constructor(url: String, username: String, password: String) : this(url, null) {
        async {
            // Retrieving existing tokens for [applicationName]
            accessToken = access.listAccessTokens(AccessAPI.createBasicAuth(username, password), username).filter {
                it.name == applicationName
            }.firstOrNull()?.sha1
            if (accessToken == null) {
                // Create new access token
                accessToken = access.createAccessToken(AccessAPI.createBasicAuth(username, password), username, AccessToken.CreateAccessTokenOption(applicationName)).sha1
            }
        }
    }

    actual constructor(url: String) : this(url, null)

    val ktor = HttpClient(engineFactory) {
        install(JsonFeature)
    }
}