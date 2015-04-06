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

    void "test validating various Artist instances"() {
        given:
        def artists = []
        artists << new Artist()
        artists << new Artist(name: "Asking Alexandria")
        artists << new Artist(name: "Asking Alexandria", songsNum: 16)
        artists << new Artist(name: "Asking Alexandria", songsNum: 16, albumsNum: 3)

        when:
        artists*.validate()

        then:
        artists[0].hasErrors()
        artists[1..-1].each { !it.hasErrors() }
    }

}
