package micronaut.logbook.example

import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject


@MicronautTest
@Property(name="micronaut.http.client.read-timeout",value="10s")
@Property(name="micronaut.http.client.pool.enabled",value="true")
@Property(name="micronaut.http.client.pool.max-connections",value="10")
//@Property(name="micronaut.http.client.log-level",value="TRACE")
@Property(name="micronaut.server.netty.log-level",value="DEBUG")
class HelloControllerKeepAliveSpec extends Specification {

    @Inject
    HelloClient client

    void "test keep alive hello world"() {
        expect:
        client.hello("Fred") == "Hello Fred!"
        and:
        client.hello("Tom") == "Hello Tom!"
    }
}