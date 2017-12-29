package de.jonasfranz.gitea.client.requests

import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.models.Status

actual class StatusAPI(client: Client) : API(client) {
    actual suspend fun createStatus(
            owner: String,
            repository: String,
            sha: String,
            options: Status.CreateStatusOption

    ): Status.Status {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual suspend fun listStatuses(
            owner: String,
            repository: String,
            sha: String,
            page: Int
    ): List<Status.Status> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual suspend fun getCombinedStatus(
            owner: String,
            repository: String,
            sha: String
    ): Status.CombinedStatus {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}