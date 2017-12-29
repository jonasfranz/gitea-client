package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.Repository

actual class RepositoriesAPI(client: Client) : API(client) {
    actual suspend fun listMyRepos(): List<Repository> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual suspend fun listUserRepos(username: String): List<Repository> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual suspend fun getRepo(owner: String, reponame: String): List<Repository> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}