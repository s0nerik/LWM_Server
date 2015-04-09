package lwm_server

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class MeController {

    static responseFormats = ['json']
    static allowedMethods = [show: "GET"]

    def show() {
        respond request.user, [status: OK]
    }

}
