package lwm_server

class Artist {

    String name;
    int songsNum;
    int albumsNum;

    static hasMany = [users: User]
    static belongsTo = [User]

    static constraints = {
    }
}
