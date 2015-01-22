package lwm_server

class Song {

    String title
    int duration
    int year

    static belongsTo = [album: Album]

    static constraints = {
    }

    static mapping = {
        table 'songs'
    }
}
