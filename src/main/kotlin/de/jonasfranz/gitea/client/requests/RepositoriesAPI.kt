package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.models.Repository

expect class RepositoriesAPI : API {
    suspend fun listMyRepos(): List<Repository>

    suspend fun listUserRepos(username: String): List<Repository>

    suspend fun getRepo(owner: String, reponame: String): List<Repository>
}

/**
 * Resource is equal => convenient function which calls [listOrgRepos].
 * @see RepositoriesAPI.listOrgRepos
 */
suspend fun RepositoriesAPI.listOrgRepos(org: String): List<Repository> {
    return this.listUserRepos(org)
}