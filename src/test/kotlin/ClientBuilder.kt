import de.jonasfranz.gitea.client.Client


object ClientSettings {
    val URL = "https://your.gitea.instance/api/v1/"
    val Username = "username"
    val Password = "password"
    var DefaultAccessToken = ""
    val ApplicationName = "ClientTest"

    fun onlyURL() = Client(URL).apply { applicationName = ApplicationName }
    fun usernamePasswordClient() = Client(URL, Username, Password).apply { applicationName = ApplicationName }
    fun defaultClient() = Client(URL, DefaultAccessToken).apply { applicationName = ApplicationName }
}

expect fun <T> runBlocking(block: suspend () -> T): T