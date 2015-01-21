package lwm_server

class Artist {

    String name
    int songsNum
    int albumsNum

    static hasMany = [albums: Album]

    static constraints = {
    }

    static mapping = {
        table 'artists'
    }
}
