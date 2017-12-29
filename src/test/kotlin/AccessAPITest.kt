import de.jonasfranz.gitea.client.Client
import de.jonasfranz.gitea.client.requests.AccessAPI
import de.jonasfranz.gitea.client.requests.createBasicAuth
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AccessAPITest {

    lateinit var client: Client
    lateinit var accessAPI: AccessAPI

    @BeforeTest
    fun constructClient() {
        client = ClientSettings.onlyURL()
        accessAPI = AccessAPI(client)
    }

    @Test
    fun testListAccessTokens() {
        val tokens = runBlocking {
            accessAPI.listAccessTokens(
                    AccessAPI.createBasicAuth(ClientSettings.Username, ClientSettings.Password),
                    ClientSettings.Username)
        }
        assertTrue(message = "Theire must be at least one existing access key") {
            tokens.isNotEmpty()
        }

    }
}
