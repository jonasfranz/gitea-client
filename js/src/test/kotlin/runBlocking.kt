import de.jonasfranz.gitea.client.utils.async

actual fun <T> runBlocking(block: suspend () -> T) {
    // Convert to JS-Promise
    async(block)
}