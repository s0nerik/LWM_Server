package lwm_server

import grails.converters.JSON

import static org.springframework.http.HttpStatus.OK

class FavoritesController {

    static responseFormats = ['json']
    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def show() {
        respond(request.user.favorites.toArray() as JSON, [status: OK])
    }
}
