package lwm_server

class User {

    String name;
    String email;
    String password;

    static constraints = {
        name size: 2..50, nullable: true
        email email: true, unique: true, blank: false
        password size: 8..20, blank: false
    }

}
