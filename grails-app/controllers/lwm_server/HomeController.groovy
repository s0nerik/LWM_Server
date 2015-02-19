package lwm_server

class HomeController {

    def index() {
        def users = User.list()
        [users: users]
    }

}
