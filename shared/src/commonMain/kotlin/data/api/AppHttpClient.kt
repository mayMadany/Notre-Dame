package data.api

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.defaultSerializer

/**
 * Created by Sonphil on 18-05-19.
 */

object AppHttpClient {
    val httpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = defaultSerializer()
            }

            install(HttpCookies) {
                // Will keep an in-memory map with all the cookies from previous requests.
                storage = AcceptAllCookiesStorage()
            }
        }
    }
}