package de.jonasfranz.gitea.client

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.FuelManager
import de.jonasfranz.gitea.client.requests.Request
import de.jonasfranz.gitea.client.requests.Response
import kotlin.coroutines.experimental.suspendCoroutine

actual class Client actual constructor(
        url: String,
        actual var accessToken: String? = null) {
    actual var applicationName: String = "kt-client"

    private var _url = url
    actual var url
        get() = _url
        set(value) {
            _url = value
            FuelManager.instance.basePath = value
        }

    init {
        FuelManager.instance.basePath = url
    }

    actual suspend fun sendRequest(request: Request): Response = suspendCoroutine { c ->
        val req = with(request) {
            when (method) {
                Request.Method.GET -> path.httpGet()
                Request.Method.POST -> path.httpPost()
                Request.Method.PUT -> path.httpPut()
                Request.Method.DELETE -> path.httpDelete()
                Request.Method.PATCH -> path.httpPatch()
                Request.Method.HEAD -> path.httpHead()
                else -> throw Exception(method.name + " is not supported by JVM")
            }
        }

        if (request.body != null) req.body(request.body.toString())
        req.header(request.headers)
        req.response { r, response, result ->
            if (result.component2() != null) {
                println(r.url.toString())
                c.resumeWithException(result.component2()!!)
                return@response
            }
            c.resume(response.toResponse())
        }
    }

    fun com.github.kittinunf.fuel.core.Response.toResponse(): Response {
        return Response(
                status = this.statusCode,
                responseText = String(data),
                headers = this.headers.map { entry -> entry.key to entry.value.first() }.toMap()
        )
    }
}