package de.jonasfranz.gitea.client.requests.exceptions

import de.jonasfranz.gitea.client.requests.Response

class HttpErrorException(resp: Response) : Exception(resp.responseText ?: "Status ${resp.status}") {
}