package de.jonasfranz.gitea.client.requests

data class Response(
        val status: Int = 200,
        val responseText: String? = null,
        val headers: Map<String, String> = emptyMap()
) {
    val successful: Boolean
        get() = (status / 100) == 2
}