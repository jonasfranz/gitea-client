package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.AccessToken
import de.jonasfranz.gitea.client.utils.Base64
import kotlinx.serialization.KSerializer
import kotlinx.serialization.list
import kotlinx.serialization.serializer

val accessListSerial: KSerializer<List<AccessToken>> = AccessToken::class.serializer().list

class AccessAPI(client: Client) : API(client) {
    suspend fun listAccessTokens(
            username: String,
            password: String
    ): List<AccessToken> = request {
        method(Request.Method.GET)
        path("users/$username/tokens")
        basicAuth(username, password)
    }.sendAndReceiveList()


    suspend fun createAccessToken(
            username: String,
            password: String,
            opts: AccessToken.CreateAccessTokenOption
    ): AccessToken = request {
        method(Request.Method.POST)
        path("users/$username/tokens")
        basicAuth(username, password)
        body(opts)
    }.send()

    companion object {
        fun createBasicAuth(username: String, password: String): String {
            val encodedUsernamePassword = Base64.Encoder.encodeToString("$username:$password")
            return "Basic $encodedUsernamePassword"
        }
    }
}