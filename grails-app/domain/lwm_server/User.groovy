package lwm_server

class User {

    String name;
    String email;
    String password;
    String avatar_url;

    static hasMany = [songs: Song]

    static constraints = {
        name size: 2..50, blank: true
        email email: true, unique: true, blank: false
        password size: 8..20, blank: false
        avatar_url nullable: true
    }

}
