package de.jonasfranz.gitea.client

import de.jonasfranz.gitea.client.requests.Request
import de.jonasfranz.gitea.client.requests.Response
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.experimental.suspendCoroutine


actual class Client actual constructor(
        actual var url: String,
        actual var accessToken: String? = null) {
    actual var applicationName = "js-client"

    actual suspend fun sendRequest(request: Request): Response = suspendCoroutine { c ->
        val xhr = XMLHttpRequest()
        xhr.onreadystatechange = {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                c.resume(Response(
                        status = xhr.status.toInt(),
                        headers = xhr.getAllResponseHeaders()
                                .trim()
                                .split(Regex("""/[\r\n]+/"""))
                                .associateBy({ it.split(": ").first() }, { it.replace(it.split(": ").first() + ": ", "") }),
                        responseText = xhr.response as? String
                ))
            }
            null
        }
        request.headers.forEach {
            xhr.setRequestHeader(it.key, it.value)
        }
        xhr.open(request.method.name, url + request.path)
        if (request.body == null) {
            xhr.send(request.body)
        } else {
            xhr.send()
        }
    }
}