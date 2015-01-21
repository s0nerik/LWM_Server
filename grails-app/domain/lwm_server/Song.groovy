package lwm_server

class Song {

    String title
    int duration
    int year

//    static hasMany = [users: User]
    static belongsTo = [album: Album]

    static constraints = {
    }

    static mapping = {
        table 'songs'
    }
}
