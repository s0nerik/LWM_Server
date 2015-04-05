package lwm_server

class Playlist {

    String name
    Date created
    Date lastUpdated
    int songsNum

    static hasMany = [songs: Song]
    static belongsTo = [user: User]

    static constraints = {
        name blank: true, nullable: true
        lastUpdated nullable: true
        songsNum min: 0
    }
}