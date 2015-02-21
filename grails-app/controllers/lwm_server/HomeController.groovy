package lwm_server

class HomeController {

    def springSecurityService

    def index() {
        def currentUser = springSecurityService.currentUser
        [user: currentUser]
    }

}
