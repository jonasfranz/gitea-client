package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.requests.API
import de.jonasfranz.gitea.client.requests.Request
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
) : API() {

    override fun request(block: Request.() -> Unit): Request {
        // Enable optionalAuthentication by default for all Repository requests
        return super.request(block).apply { optionalAuthentication() }
    }

    // Cannot be a val beacause of problems with the serialization plugin
    private fun repoBasePath() = "repos/${owner.username}/$name/"

    /**
     * A shortcut for /:owner/:repo/[subPath]
     */
    private infix fun Request.repoPath(subPath: String) {
        path(repoBasePath() + subPath)
    }

    /**
     * Lists all the branches of the repository
     */
    suspend fun listBranches(): List<Branch> = request {
        optionalAuthentication()
        repoPath("branches")
    }.sendAndReceiveList()

    /**
     * Get one branch's information of one repository
     */
    suspend fun getBranch(branch: String): Branch = request {
        repoPath("branches/$branch")
    }.send()

    /**
     * Creates a new Status for a given Commit
     * @param sha Commit's SHA hash
     */
    suspend fun createStatus(
            sha: String,
            options: Status.CreateStatusOption
    ): Status.Status = request {
        method(Request.Method.POST)
        repoPath("statuses/$sha")
        body(options)
    }.send()

    /**
     * Returns all statuses for a given Commit
     * @param sha Commit's SHA hash
     */
    suspend fun listStatuses(
            sha: String,
            page: Int
    ): List<Status.Status> = request {
        repoPath("commits/$sha/statuses?page=$page")
    }.sendAndReceiveList()

    /**
     * Returns the CombinedStatus for a given commit [sha]
     * @param sha Commit's SHA hash
     */
    suspend fun getCombinedStatus(
            sha: String
    ): Status.CombinedStatus = request {
        repoPath("commits/$sha/status")
    }.send()

    companion object {
        @Serializable
        data class Permission(
                var admin: Boolean = false,
                var push: Boolean = false,
                var pull: Boolean = false
        )
    }
}