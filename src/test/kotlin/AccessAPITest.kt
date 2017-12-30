import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.access
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AccessAPITest {

    lateinit var client: Client

    @BeforeTest
    fun constructClient() {
        client = ClientSettings.onlyURL()
    }

    @Test
    fun testListAccessTokens() {
        val tokens = runBlocking {
            client.access.listAccessTokens(ClientSettings.Username, ClientSettings.Password)
        }
        assertTrue(message = "Theire must be at least one existing access key") {
            tokens.isNotEmpty()
        }

    }
}
