package lwm_server

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Song)
@Mock([Album, Artist])
class SongSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test creation of a new Song without specifying Album"() {
        given:
        Song s = new Song()

        expect:
        s.validate() == false
    }

    void "test creation of a new Song with specifying Album"() {
        given:
        Artist artist = new Artist(name: "Asking Alexandria")
        Album album = new Album(title: "From Death To Destiny", artist: artist)
        Song s = new Song(title: "Don't Pray For Me", album: album, duration: 3*1000*60)

        expect:
        s.validate() == true
    }

}
