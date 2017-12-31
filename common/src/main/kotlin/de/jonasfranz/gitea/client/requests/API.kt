package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.requests.exceptions.HttpErrorException
import de.jonasfranz.gitea.client.requests.exceptions.InvalidTokenException
import de.jonasfranz.gitea.client.utils.JSONDate
import kotlinx.serialization.SerialContext
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import kotlinx.serialization.json.JSON as KJSON

open class API(var client: Client) {
    fun Request.authentication() {
        this.tokenAuth(client.accessToken ?: throw InvalidTokenException())
    }

    fun Request.optionalAuthentication() {
        try {
            authentication()
        } catch (ex: InvalidTokenException) {/* Ignore because authentication is optional */
        }
    }

    val customJSON: KJSON
        get() {
            val context = SerialContext()
            context.registerSerializer(JSONDate::class, JSONDate.Companion)
            return KJSON(context = context, nonstrict = true)
        }

    suspend inline fun <reified T : Any> Request.send(): T {
        val resp = client.sendRequest(this)
        if (!resp.successful) {
            throw HttpErrorException(resp)
        }
        return customJSON.parse(T::class.serializer(), resp.responseText ?: throw Exception("Empty response"))
    }

    suspend inline fun <reified T : Any> Request.sendAndReceiveList(): List<T> {
        val resp = client.sendRequest(this)
        if (!resp.successful) {
            throw HttpErrorException(resp)
        }
        return customJSON.parse(T::class.serializer().list, resp.responseText ?: throw Exception("Empty response"))
    }

    suspend fun Request.sendRaw(): Response {
        return client.sendRequest(this)
    }

    fun request(block: Request.() -> Unit): Request = Request().apply(block)
}