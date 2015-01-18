package lwm_server

class Album {

    String title;
    String coverUrl;
    int year;

    static hasMany = [users: User]

    static constraints = {
    }
}
