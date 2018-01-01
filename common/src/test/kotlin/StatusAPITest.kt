import de.jonasfranz.gitea.client.models.Status
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StatusAPITest : RepositoryTest() {
    private val firstCommit = "47d862496f71143df2505de2b0a98ea3efe3e07b"

    @Test
    fun testCreateStatus() {
        val result = runBlocking {
            repository.createStatus(firstCommit,
                    Status.CreateStatusOption(
                            state = "warning",
                            context = "API Client Test",
                            description = "A status created during an automated test",
                            targetURL = "https://github.com/JonasFranzDEV/gitea-client"
                    )
            )
        }
        assertEquals("warning", result.state)
    }

    @Test
    fun testListStatuses() {
        val results = runBlocking { repository.listStatuses(firstCommit, 1) }
        assertTrue("There must be at least one status for first commmit") {
            results.isNotEmpty()
        }
    }

    @Test
    fun testCombinedStatus() {
        val result = runBlocking { repository.getCombinedStatus(firstCommit) }
        assertEquals(repository, result.repository)
        assertEquals(result.totalCount, result.statuses.count())
        assertTrue("There must be at least one status for first commmit") {
            result.statuses.isNotEmpty()
        }
    }
}