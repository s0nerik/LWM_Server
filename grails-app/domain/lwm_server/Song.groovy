package lwm_server

class Song {

    String title;
    int duration;
    int year;

    static hasMany = [users: User]

    static constraints = {
    }
}
