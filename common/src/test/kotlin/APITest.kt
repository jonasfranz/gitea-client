import de.jonasfranz.gitea.client.Client
import kotlin.test.BeforeTest

open class APITest {
    lateinit var client: Client

    @BeforeTest
    fun constructClient() {
        client = with(ClientSettings) {
            when (type) {
                ClientType.Default -> defaultClient()
                ClientType.OnlyURL -> onlyURL()
                ClientType.UsernamePassword -> usernamePasswordClient()
            }
        }
    }

    open val type: ClientType = ClientType.Default

    enum class ClientType {
        OnlyURL,
        Default,
        UsernamePassword
    }
}