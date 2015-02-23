package lwm_server

class HomeController {

    def springSecurityService

    def index() {
        def currentUser = springSecurityService.principal.userProfile.attributes
        [user: currentUser]
    }

}
