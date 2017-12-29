package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import org.w3c.xhr.XMLHttpRequest

actual abstract class API actual constructor(val client: Client) {

    fun XMLHttpRequest.openPath(path: String, method: Method = Method.POST) {
        open(method.name, client.url + path)
    }

    fun XMLHttpRequest.authentication() {
        this.setRequestHeader("Authorization", "token ${client.accessToken}")
    }

    fun XMLHttpRequest.basicAuth(username: String, password: String) {
        this.setRequestHeader("Authorization", AccessAPI.createBasicAuth(username, password))
    }

    fun XMLHttpRequest.basicAuth(token: String) {
        this.setRequestHeader("Authorization", token)
    }

    enum class Method {
        GET, POST, PUT, DELETE, CONNECT, OPTIONS, HEAD, TRACE, PATCH
    }
}