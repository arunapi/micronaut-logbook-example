package micronaut.logbook.example

import io.micronaut.http.HttpHeaders
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client('/')
interface HelloClient {

    @Get("/hello/{name}")
    @Header(name = HttpHeaders.ACCEPT,value = MediaType.TEXT_PLAIN)
    String hello(String name)
}
