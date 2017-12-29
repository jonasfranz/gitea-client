package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.AccessToken
import de.jonasfranz.gitea.client.utils.request
import de.jonasfranz.gitea.client.utils.sendJSON

actual class AccessAPI actual constructor(client: Client) : API(client) {
    actual suspend fun listAccessTokens(basicAuth: String, username: String): Array<AccessToken> = request {
        basicAuth(basicAuth)
        openPath("users/$username/tokens", Method.GET)
        send()
    }


    actual suspend fun createAccessToken(basicAuth: String, username: String, opts: AccessToken.CreateAccessTokenOption): AccessToken = request {
        basicAuth(basicAuth)
        openPath("users/$username/tokens")
        sendJSON(opts)
    }

    actual companion object {}
}