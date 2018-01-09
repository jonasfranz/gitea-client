import de.jonasfranz.gitea.client.repositories
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoriesAPITest : APITest() {
    @Test
    fun testMyRepos() = runBlocking {
        val repos = client.repositories.listMyRepos()
        assertEquals(1, repos.size, "number of repositories must be 1")
        val testRepo = repos.first()
        assertEquals("test", testRepo.name)
        assertEquals("test repo", testRepo.description)
    }

    @Test
    fun testListUserRepos() = runBlocking {
        val repos = client.repositories.listUserRepos("JonasFranzDEV")
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
    fun testGetRepo() = runBlocking {
        val repo = client.repositories.getRepo("JonasFranzDEV", "Crowdin")
        assertEquals("Crowdin", repo.name)
        assertEquals(true, repo.mirror)
    }
}
