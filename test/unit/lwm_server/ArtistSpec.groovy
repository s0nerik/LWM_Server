package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Artist)
class ArtistSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating Artist without a name"() {
        given:
        Artist a = new Artist()

        expect:
        a.validate() == false
    }

    void "test validating Artist with a name"() {
        given:
        Artist a = new Artist(name: "Asking Alexandria")

        expect:
        a.validate() == true
    }

}
