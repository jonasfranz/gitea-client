import de.jonasfranz.gitea.client.repositories
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoriesAPITest : APITest() {
    @Test
    fun testMyRepos() {
        val repos = runBlocking { client.repositories.listMyRepos() }
        assertEquals(1, repos.size, "number of repositories must be 1")
        val testRepo = repos.first()
        assertEquals("test", testRepo.name)
        assertEquals("test repo", testRepo.description)
    }

    @Test
    fun testListUserRepos() {
        val repos = runBlocking { client.repositories.listUserRepos("JonasFranzDEV") }
        assertTrue("number of repositories must be at least 1") {
            repos.size >= 1
        }
        assertTrue("repos must contain a repo called \"Crowdin\"") {
            repos.any {
                it.name == "Crowdin"
            }
        }
    }

    @Test
    fun testGetRepo() {
        val repo = runBlocking { client.repositories.getRepo("JonasFranzDEV", "Crowdin") }
        assertEquals("Crowdin", repo.name)
        assertEquals(true, repo.mirror)
    }
}
