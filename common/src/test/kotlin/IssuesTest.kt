import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class IssuesTest : RepositoryTest() {
    @Test
    fun testListIssues() = runBlocking {
        val issues = repository.listIssues()
        assertTrue { issues.isNotEmpty() }
    }

    @Test
    fun testGetIssue() {
        val issue = runBlocking { repository.getIssue(1) }
        assertEquals("Test-Issue", issue.title, "issue title")
        assertTrue { issue.labels.isNotEmpty() }
        assertNotNull(issue.milestone)
    }
}