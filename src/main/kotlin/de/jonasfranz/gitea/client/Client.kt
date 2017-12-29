package de.jonasfranz.gitea.client

expect class Client(url: String, accessToken: String?) {
    constructor(url: String, username: String, password: String)
    constructor(url: String)

    var applicationName: String
}