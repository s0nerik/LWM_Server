package lwm_server

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Album)
@Mock(Artist)
class AlbumSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating an Album without an Artist"() {
        given:
        Album a = new Album(title: "Master Of Puppets")

        expect:
        a.validate() == false
    }

    void "test validating an Album without a title"() {
        given:
        Artist artist = new Artist(name: "Asking Alexandria")
        Album a = new Album(artist: artist)

        expect:
        a.validate() == false
    }

    void "test validating an Album with an Artist"() {
        given:
        Artist artist = new Artist(name: "Asking Alexandria")
        Album a = new Album(title: "From Death To Destiny", artist: artist)

        expect:
        a.validate() == true
    }

}
