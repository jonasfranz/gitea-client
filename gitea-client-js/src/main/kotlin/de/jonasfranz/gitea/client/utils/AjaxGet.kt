package de.jonasfranz.gitea.client.utils

import de.jonasfranz.gitea.client.requests.API
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine
import kotlin.js.Promise
import kotlinx.serialization.json.JSON as KJSON

suspend inline fun <reified T : Any> API.httpGet(url: String): T = request<T> {
    open("GET", url)
    send()
}

suspend inline fun <reified T : Any> API.request(crossinline block: XMLHttpRequest.() -> Unit): T = suspendCoroutine { c ->
    val xhr = XMLHttpRequest()
    xhr.onreadystatechange = {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status / 100 == 2) {
                c.resume(KJSON.parse(xhr.responseText))
            } else {
                c.resumeWithException(RuntimeException("HTTP error: ${xhr.status}"))
            }
        }
        null
    }
    xhr.apply(block)
}

fun XMLHttpRequest.sendJSON(body: Any) {
    setRequestHeader("Content-Type", "application/json")
    send(KJSON.stringify(body))
}

fun <T> async(x: suspend () -> T): Promise<T> {
    return Promise { resolve, reject ->
        x.startCoroutine(object : Continuation<T> {
            override val context = EmptyCoroutineContext

            override fun resume(value: T) {
                resolve(value)
            }

            override fun resumeWithException(exception: Throwable) {
                reject(exception)
            }
        })
    }
}