package lwm_server

import com.odobo.grails.plugin.springsecurity.rest.token.storage.TokenNotFoundException

class AuthFilters {

    def tokenStorageService

    def filters = {
        restActionsAuth(controller:'home|index|*auth*', action:'*', invert: true) {
            before = {

                String authToken = request.getHeader("Authorization")?.split(" ")?.getAt(1)

                if (!authToken) return false

                try {
                    def u = tokenStorageService.loadUserByToken(authToken)
                    request.user = User.findByUsername u.username

                    return true
                } catch (TokenNotFoundException e) {
                    log.error(e)
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
