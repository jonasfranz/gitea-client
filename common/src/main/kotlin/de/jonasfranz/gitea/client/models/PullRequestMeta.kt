package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.JSONDate
import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PullRequestMeta(
        @SerializedName("merged")
        var hasMerged: Boolean,
        @SerializedName("merged_at")
        var mergedAt: JSONDate? = null
)