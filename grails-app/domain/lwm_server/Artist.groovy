package lwm_server

class Artist {

    String name
    int songsNum
    int albumsNum

    static hasMany = [albums: Album, songs: Song]

    static constraints = {
        name blank: false, nullable: false
//        name blank: true, nullable: true, unique: true
        songsNum blank: true, nullable: true
        albumsNum blank: true, nullable: true
    }

    static mapping = {
        table 'artists'
        songsNum defaultValue: 0
        albumsNum defaultValue: 0
    }
}
