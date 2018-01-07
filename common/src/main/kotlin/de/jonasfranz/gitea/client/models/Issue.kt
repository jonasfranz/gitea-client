package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.requests.API
import de.jonasfranz.gitea.client.utils.JSONDate
import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(
        var id: Int,
        var url: String,
        @SerializedName("number")
        var index: Int,
        @SerializedName("user")
        var poster: User? = null,
        var title: String,
        var body: String,
        var labels: List<Label> = listOf(),
        var milestone: Milestone? = null,
        var assignee: User? = null,
        var state: Issue.State,
        @SerializedName("comments")
        var numberOfComments: Int,
        @SerializedName("created_at")
        var created: JSONDate,
        @SerializedName("updated_at")
        var updated: JSONDate,
        @SerializedName("pull_request")
        var pullRequest: PullRequestMeta? = null
) : API() {
    enum class State {
        open, closed;
    }
}