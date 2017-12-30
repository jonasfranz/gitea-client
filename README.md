# API-Client for Gitea
[![Download](https://api.bintray.com/packages/jonasfranzdev/gitea-client/gitea-client/images/download.svg)](https://bintray.com/jonasfranzdev/gitea-client/gitea-client/_latestVersion)
[![Build status](https://drone.jonasfranz.de/api/badges/JonasFranzDEV/gitea-client/status.svg)](https://drone.jonasfranz.de/JonasFranzDEV/gitea-client)

A Kotlin based API client for [Gitea](https://github.com/go-gitea/gitea).

This project is actually under construction.
## Support
- [X] JavaScript (JS)
- [X] Java (JVM)

## Using gitea-client
### Gradle-based project
Add the gitea client repository to your module
```groovy
repositories {
    maven { url "https://dl.bintray.com/jonasfranzdev/gitea-client" } 
}
```
#### Common
If you have a [multi platform project](https://kotlinlang.org/docs/reference/multiplatform.html) please
add the `common` dependency to your `common` module. Please also add the JVM and JS dependency to your JS/JVM module.
```groovy
compile 'de.jonasfranz.gitea:gitea-client-common:0.0.2'
```
#### JVM
```groovy
compile 'de.jonasfranz.gitea:gitea-client:0.0.2'
```
#### JS
```groovy
compile 'de.jonasfranz.gitea:gitea-client-js:0.0.2'
```


## License



This project is licensed under the MIT License. See the [LICENSE](https://github.com/JonasFranzDEV/gitea-client/blob/master/LICENSE) file for the full license text.