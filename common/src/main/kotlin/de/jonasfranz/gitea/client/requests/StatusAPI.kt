package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.Status

class StatusAPI(client: Client) : API(client) {
    suspend fun createStatus(
            owner: String,
            repository: String,
            sha: String,
            options: Status.CreateStatusOption

    ): Status.Status = TODO()

    suspend fun listStatuses(
            owner: String,
            repository: String,
            sha: String,
            page: Int
    ): List<Status.Status> = TODO()

    suspend fun getCombinedStatus(
            owner: String,
            repository: String,
            sha: String
    ): Status.CombinedStatus = TODO()
}