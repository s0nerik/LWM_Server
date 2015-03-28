package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Settings)
class SettingsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating empty settings"() {
        given:
        Settings s = new Settings(user: new User())

        expect:
        s.validate() == true
    }
}
