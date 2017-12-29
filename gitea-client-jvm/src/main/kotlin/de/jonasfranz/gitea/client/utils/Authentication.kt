package de.jonasfranz.gitea.client.utils

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

fun HttpRequestBuilder.authentication(accessToken: String) {
    header("Authorization", "token $accessToken")
}