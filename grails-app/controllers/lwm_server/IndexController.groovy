package lwm_server

class IndexController {

    def springSecurityService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect(controller: 'home', action: 'index')
        }
    }
}
