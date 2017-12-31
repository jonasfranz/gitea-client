package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.JSONDate
import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
        var id: Int,
        var owner: User,
        var name: String,
        @SerializedName("full_name")
        var fullName: String,
        var description: String,
        var empty: Boolean,
        var private: Boolean,
        var fork: Boolean,
        @Optional
        var parent: Repository? = null,
        var mirror: Boolean,
        var size: Int,
        @SerializedName("html_url")
        var htmlURL: String,
        @SerializedName("ssh_url")
        var sshURL: String,
        @SerializedName("clone_url")
        var cloneURL: String,
        var website: String,
        @SerializedName("stars_count")
        var stars: Int,
        @SerializedName("forks_count")
        var forks: Int,
        @SerializedName("watchers_count")
        var watchers: Int,
        @SerializedName("open_issues_count")
        var openIssues: Int,
        @SerializedName("default_branch")
        var defaultBranch: String,
        @SerializedName("created_at")
        var createdAt: JSONDate,
        @SerializedName("updated_at")
        var updatedAt: JSONDate,
        var permissions: Permission
) {
    companion object {
        @Serializable
        data class Permission(
                var admin: Boolean = false,
                var push: Boolean = false,
                var pull: Boolean = false
        )
    }
}