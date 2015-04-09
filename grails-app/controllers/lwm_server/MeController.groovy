package lwm_server

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = false)
class MeController {

    static responseFormats = ['json']
    static allowedMethods = [show: "GET", update: "PUT"]

    def show() {
        respond request.user, [status: OK]
    }

    def update(User user) {
        request.user = user
        request.user.save()
        respond request.user, [status: OK]
    }

}
