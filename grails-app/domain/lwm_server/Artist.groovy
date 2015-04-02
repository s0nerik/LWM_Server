package lwm_server

class Artist {

    String name
    int songsNum
    int albumsNum

    static hasMany = [albums: Album]

    static constraints = {
        name blank: false, nullable: false
        songsNum blank: true, nullable: true
        albumsNum blank: true, nullable: true
    }

    static mapping = {
        table 'artists'
        songsNum defaultValue: 0
        albumsNum defaultValue: 0
    }
}
