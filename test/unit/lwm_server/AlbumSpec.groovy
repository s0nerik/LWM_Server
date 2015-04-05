package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Album)
class AlbumSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating various albums"() {
        given:
        def albums = []
        albums << new Album()
        albums << new Album(title: "Master Of Puppets")
        albums << new Album(artist: new Artist(), title: "Master Of Puppets")
        albums << new Album(artist: new Artist(), title: "Master Of Puppets", coverUrl: "http://mamazon.com/master.jpg")
        albums << new Album(artist: new Artist(), title: "Master Of Puppets", coverUrl: "ae.mamazon.com")

        when:
        albums*.validate()

        then:
        albums[0..2].each { it.hasErrors() }
        albums[2..-2].each { !it.hasErrors() }
        albums[-1].hasErrors()
    }

}
