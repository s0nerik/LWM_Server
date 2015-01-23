package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Song)
class SongSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "can't create a new Song without specifying Album"() {
        given:
        Song s = new Song()

        expect:
        s.validate() == false
    }

}
