package lwm_server

class Song {

    String title
    int duration
    int year

    static belongsTo = [album: Album]

    static constraints = {
        title blank: false, nullable: false
        duration blank: false
        year blank: true, nullable: true
    }

    static mapping = {
        table 'songs'
    }
}
