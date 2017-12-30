import de.jonasfranz.gitea.client.Client


object ClientSettings {
    val URL = "https://git.jonasfranz.software/api/v1"
    val Username = "demo"
    val Password = "demo12345"
    var DefaultAccessToken = "df7c7006f6f4b1e0ab5ec6aed4f2a9d1da98c34b"
    val ApplicationName = "test"

    fun onlyURL() = Client(URL).apply { applicationName = ApplicationName }
    fun usernamePasswordClient() = runBlocking { Client(URL, Username, Password).apply { applicationName = ApplicationName } }
    fun defaultClient() = Client(URL, DefaultAccessToken).apply { applicationName = ApplicationName }
}

expect fun <T> runBlocking(block: suspend () -> T): T