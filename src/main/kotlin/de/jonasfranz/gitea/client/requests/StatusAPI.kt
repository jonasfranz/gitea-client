package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.models.Status

expect class StatusAPI : API {
    suspend fun createStatus(
            owner: String,
            repository: String,
            sha: String,
            options: Status.CreateStatusOption

    ): Status.Status

    suspend fun listStatuses(
            owner: String,
            repository: String,
            sha: String,
            page: Int
    ): List<Status.Status>

    suspend fun getCombinedStatus(
            owner: String,
            repository: String,
            sha: String
    ): Status.CombinedStatus
}