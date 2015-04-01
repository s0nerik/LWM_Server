import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import lwm_server.Settings
import lwm_server.User

// Place your Spring DSL code here
beans = {
    oauthUserDetailsService(LWMOauthUserDetailsService) {
        userDetailsService = ref('userDetailsService')
    }

    def userExcludes = ['class', 'accountExpired', 'accountLocked', 'passwordExpired', 'pasword', 'enabled']

    userCollectionRenderer(JsonCollectionRenderer, User) {
        excludes = userExcludes
    }

    userRenderer(JsonRenderer, User) {
        excludes = userExcludes
    }

    def settingsExcludes = ['class', 'errors', 'id', 'version']

    settingsCollectionRenderer(JsonCollectionRenderer, Settings) {
        excludes = settingsExcludes
    }

    settingsRenderer(JsonRenderer, Settings) {
        excludes = settingsExcludes
    }

}
