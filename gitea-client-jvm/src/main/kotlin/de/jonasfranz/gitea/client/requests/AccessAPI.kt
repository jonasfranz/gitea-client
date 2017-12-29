package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.AccessToken


actual class AccessAPI actual constructor(client: Client) : API(client) {
    actual suspend fun listAccessTokens(basicAuth: String, username: String): Array<AccessToken> {
        return emptyArray()
    }

    actual suspend fun createAccessToken(basicAuth: String, username: String, opts: AccessToken.CreateAccessTokenOption): AccessToken {
        return AccessToken(name = "", sha1 = "")
    }

    actual companion object {}
}