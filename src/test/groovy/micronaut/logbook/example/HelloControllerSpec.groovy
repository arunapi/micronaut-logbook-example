package micronaut.logbook.example

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject


@MicronautTest
class HelloControllerSpec extends Specification {

    @Inject
    HelloClient client

    void "test hello world"() {
        expect:
        client.hello("Fred") == "Hello Fred!"
        and:
        client.hello("Tom") == "Hello Tom!"
    }
}