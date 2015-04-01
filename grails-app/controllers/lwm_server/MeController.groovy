package lwm_server

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class MeController {

    static responseFormats = ['json']
    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def index() {
        respond request.user, [status: OK]
    }

    @Transactional
    def update(User userInstance) {
//        def userInstance = request.user

        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.validate()
        if (userInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        userInstance.save flush: true
        respond userInstance, [status: OK]
    }

    @Transactional
    def delete() {
        def userInstance = request.user

        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.delete flush: true
        render status: NO_CONTENT
    }
}
