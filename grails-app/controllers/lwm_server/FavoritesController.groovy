package lwm_server
import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = false)
class FavoritesController {

    def lastFmService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        respond(request.user.favorites.toArray() as JSON, [status: OK])
    }

    def save(Song song) {
        lastFmService.updateSong(song)
        request.user.addToFavorites(song)
        request.user.save()
        respond(song as JSON, [status: OK])
    }
}
