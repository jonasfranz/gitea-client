package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.utils.authentication
import io.ktor.client.request.HttpRequestBuilder

actual abstract class API actual constructor(protected var client: Client) {
    fun HttpRequestBuilder.auth() {
        if (client.accessToken != null) {
            authentication(client.accessToken!!)
        }
    }
}