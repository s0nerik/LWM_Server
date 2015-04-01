import lwm_server.Role

class BootStrap {

    def init = { servletContext ->
        new Role(authority: 'ROLE_ADMIN').save()
        new Role(authority: 'ROLE_USER').save()

//        JSON.registerObjectMarshaller(Settings) { // Remove 'class' entry from all JSON's
//            return it.properties.findAll {k,v -> k != 'class'}
//        }
//
//        JSON.registerObjectMarshaller(User) { // Remove 'class' entry from all JSON's
//            return it.properties.findAll {k,v -> k != ('class' || 'accountExpired' || 'accountLocked')}
//        }
    }
    def destroy = {
    }
}
