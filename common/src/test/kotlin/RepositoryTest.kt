import de.jonasfranz.gitea.client.models.Repository
import de.jonasfranz.gitea.client.repositories
import kotlin.test.BeforeTest

open class RepositoryTest : APITest() {
    protected lateinit var repository: Repository

    @BeforeTest
    fun recoverRepository() = runBlocking {
        repository = client.repositories.getRepo("demo", "test")
    }
}