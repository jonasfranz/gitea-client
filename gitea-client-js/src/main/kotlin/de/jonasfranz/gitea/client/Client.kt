package de.jonasfranz.gitea.client

actual class Client actual constructor(var url: String, var accessToken: String?) {
    actual constructor(url: String, username: String, password: String) : this(url, null) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual constructor(url: String) : this(url, null) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual var applicationName = "js-client"
}