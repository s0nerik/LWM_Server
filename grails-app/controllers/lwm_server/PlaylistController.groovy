package lwm_server

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = false)
class PlaylistController {

    def lastFmService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        respond(request.user.playlists.toArray() as JSON, [status: OK])
    }

    def save(Playlist playlist) {
        playlist.songs.each {
            lastFmService.updateSong(it)
            playlist.addToSongs(it)
            it.save()
        }

        request.user.addToPlaylists(playlist)
        playlist.save()

        request.user.save()

        respond(playlist as JSON, [status: OK])
    }

}
