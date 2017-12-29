package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.Date
import de.jonasfranz.gitea.client.utils.SerializedName

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
        var parent: Repository,
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
        var createdAt: Date,
        @SerializedName("updated_at")
        var updatedAt: Date,
        var permissions: Permission
) {
    companion object {
        data class Permission(
                var admin: Boolean = false,
                var push: Boolean = false,
                var pull: Boolean = false
        )
    }
}