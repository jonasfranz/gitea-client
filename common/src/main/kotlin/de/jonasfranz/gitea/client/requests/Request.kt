package de.jonasfranz.gitea.client.requests

import kotlinx.serialization.serializer
import kotlinx.serialization.json.JSON as KJSON

class Request() {
    enum class Method {
        GET, POST, PUT, DELETE, CONNECT, OPTIONS, HEAD, TRACE, PATCH
    }

    var method: Method = Method.GET
    var path: String = ""
    var headers: MutableMap<String, String> = mutableMapOf()
    var body: Any? = null

    infix fun method(method: Method) = apply {
        this.method = method
    }

    infix fun path(path: String) = apply {
        this.path = path
    }

    infix fun basicAuth(token: String) = apply {
        headers.put("Authorization", token)
    }

    infix fun tokenAuth(token: String) = apply {
        headers.put("Authorization", "token $token")
    }

    inline infix fun <reified T : Any> body(json: T) = apply {
        header("Content-Type", "application/json")
        body = KJSON.stringify(T::class.serializer(), json)
    }

    infix fun body(content: String) = apply {
        body = content
    }

    fun header(key: String, value: String) = apply { headers.put(key, value) }

    fun basicAuth(username: String, password: String) = apply {
        basicAuth(AccessAPI.createBasicAuth(username, password))
    }
}