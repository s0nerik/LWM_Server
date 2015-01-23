package lwm_server

class Song {

    String title
    int duration
    int year

    static belongsTo = [album: Album]

    static constraints = {
        title blank: false, nullable: false
        duration blank: false
    }

    static mapping = {
        table 'songs'
    }
}
