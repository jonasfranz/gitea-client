package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.JSONDate
import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Milestone(
        var id: Int,
        var title: String,
        var description: String,
        var state: Issue.State,
        @SerializedName("open_issues")
        var numberOfOpenIssues: Int,
        @SerializedName("closed_issues")
        var numberOfClosedIssues: Int,
        @SerializedName("closed_at")
        var closedAt: JSONDate?,
        @SerializedName("due_on")
        var deadline: JSONDate?
)