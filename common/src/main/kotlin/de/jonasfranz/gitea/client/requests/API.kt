package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.requests.exceptions.ClientNotSetException
import de.jonasfranz.gitea.client.requests.exceptions.HttpErrorException
import de.jonasfranz.gitea.client.requests.exceptions.InvalidTokenException
import de.jonasfranz.gitea.client.utils.JSONDate
import kotlinx.serialization.SerialContext
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import kotlinx.serialization.json.JSON as KJSON

open class API() {
    var client: Client? = null

    constructor(client: Client) : this() {
        this.client = client
    }

    fun Request.authentication() {
        this.tokenAuth(client?.accessToken ?: throw InvalidTokenException())
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
            // This registry is required due to problems with annotation processing in common builds
            context.registerSerializer(JSONDate::class, JSONDate.Companion)
            return KJSON(context = context, nonstrict = true)
        }

    suspend inline fun <reified T : Any> Request.send(): T {
        if (client == null) throw ClientNotSetException()
        val resp = client!!.sendRequest(this)
        if (!resp.successful) {
            throw HttpErrorException(resp)
        }
        val result = customJSON.parse(T::class.serializer(), resp.responseText ?: throw Exception("Empty response"))
        // Recovering client for models that have custom API methods like repositories.
        if (result is API) {
            result.client = client
        }
        return result
    }

    suspend inline fun <reified T : Any> Request.sendAndReceiveList(): List<T> {
        if (client == null) throw ClientNotSetException()
        val resp = client!!.sendRequest(this)
        if (!resp.successful) {
            throw HttpErrorException(resp)
        }
        val results = customJSON.parse(T::class.serializer().list, resp.responseText ?: throw Exception("Empty response"))
        // Recovering client for models that have custom API methods like repositories.
        results.filter { it is API }.forEach { (it as API).client = client }
        return results
    }

    suspend fun Request.sendRaw(): Response {
        if (client == null) throw ClientNotSetException()
        return client!!.sendRequest(this)
    }

    open fun request(block: Request.() -> Unit): Request = Request().apply(block)
}