package lwm_server

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class PlaylistController {

    static responseFormats = ['json']
    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def show() {
        respond(request.user.playlists.toArray() as JSON, [status: OK])
    }

}
