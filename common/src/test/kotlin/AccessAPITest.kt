import de.jonasfranz.gitea.client.access
import de.jonasfranz.gitea.client.models.AccessToken
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AccessAPITest : APITest() {

    override val type: ClientType = ClientType.OnlyURL

    @Test
    fun testListAccessTokens() = runBlocking {
        val tokens = client.access.listAccessTokens(ClientSettings.Username, ClientSettings.Password)
        assertTrue(message = "Their must be at least one existing access key") {
            tokens.isNotEmpty()
        }
    }

    @Test
    fun testCreateAccessToken() = runBlocking {
        val token = client.access.createAccessToken(ClientSettings.Username, ClientSettings.Password, AccessToken.CreateAccessTokenOption("t3st"))
        assertEquals("t3st", token.name)
        assertTrue(message = "The given token is to short") {
            token.sha1.length > 5
        }
    }
}
