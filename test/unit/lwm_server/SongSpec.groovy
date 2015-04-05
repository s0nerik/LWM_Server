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

    void "test validating various songs"() {
        given:
        def songs = []
        songs << new Song()
        songs << new Song(album: new Album())
        songs << new Song(title: "Hello, Dolly", album: new Album())
        songs << new Song(title: "Hello, Dolly", duration: 16000, album: new Album())
        songs << new Song(title: "Hello, Dolly", duration: 16000, album: new Album(), artist: new Artist())

        when:
        songs*.validate()

        then:
        songs[0..3].each { it.hasErrors() }
        !songs[-1].hasErrors()
    }

}
