package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.Repository

class RepositoriesAPI(client: Client) : API(client) {
    suspend fun listMyRepos(): List<Repository> = TODO()

    suspend fun listUserRepos(username: String): List<Repository> = TODO()

    suspend fun getRepo(owner: String, reponame: String): List<Repository> = TODO()
}

/**
 * Resource is equal => convenient function which calls [listOrgRepos].
 * @see RepositoriesAPI.listOrgRepos
 */
suspend fun RepositoriesAPI.listOrgRepos(org: String): List<Repository> {
    return this.listUserRepos(org)
}