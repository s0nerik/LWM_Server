package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Playlist)
class PlaylistSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating various playlists"() {
        given:
        def playlist1 = new Playlist(user: new User())
        def playlist2 = new Playlist(user: new User(), created: new Date(1337))
        def playlist3 = new Playlist(user: new User(), created: new Date(1337), name: "")
        def playlist4 = new Playlist(user: new User(), created: new Date(1337), name: "playlist")

        expect:
        playlist1.validate() == false
        playlist2.validate() == true
        playlist3.validate() == true
        playlist4.validate() == true
    }
}
