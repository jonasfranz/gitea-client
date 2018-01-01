# API-Client for Gitea
[![Download](https://api.bintray.com/packages/jonasfranzdev/gitea-client/gitea-client/images/download.svg)](https://bintray.com/jonasfranzdev/gitea-client/gitea-client/_latestVersion)
[![Build status](https://drone.jonasfranz.de/api/badges/JonasFranzDEV/gitea-client/status.svg)](https://drone.jonasfranz.de/JonasFranzDEV/gitea-client)

A Kotlin based API client for [Gitea](https://github.com/go-gitea/gitea).

This project is actually under construction.
## Support
- [X] JavaScript (JS)
- [X] Java (JVM)
## Example
```kotlin
fun createStatus() = async {
    val client = Client("https://your.gitea.instance/api/v1", "YOUR-API-KEY")
    val repository = client.repositories.getRepo("organization", "reponame")
    val status = repository.createStatus("COMMIT-SHA",
                        Status.CreateStatusOption(
                                state = "warning",
                                context = "API Client Test",
                                description =  "A status created during an automated test",
                                targetURL = "https://github.com/JonasFranzDEV/gitea-client"
                        )
                )
    println(status.createdAt.getTime())
}
```

## Using gitea-client
### Gradle-based project
Add the gitea client repository to your module
```groovy
repositories {
    jcenter()
    maven { url "https://kotlin.bintray.com/kotlinx" }
    maven { url "https://dl.bintray.com/jonasfranzdev/gitea-client" }
}
```
#### Common
If you have a [multi platform project](https://kotlinlang.org/docs/reference/multiplatform.html) please
add the `common` dependency to your `common` module. Please also add the JVM and JS dependency to your JS/JVM module.
```groovy
compile 'de.jonasfranz.gitea:gitea-client-common:0.0.6'
```
#### JVM
```groovy
compile 'de.jonasfranz.gitea:gitea-client:0.0.6'
```
#### JS
```groovy
compile 'de.jonasfranz.gitea:gitea-client-js:0.0.6'
```


## License



This project is licensed under the MIT License. See the [LICENSE](https://github.com/JonasFranzDEV/gitea-client/blob/master/LICENSE) file for the full license text.
