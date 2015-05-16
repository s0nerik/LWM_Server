package lwm_server

import org.grails.databinding.BindingFormat

class Playlist {

    String name
    @BindingFormat('yyyy-MM-dd HH:mm:ss z')
    Date created
    Date lastUpdated
    int songsNum

    static hasMany = [songs: Song]
    static belongsTo = [user: User]

    static constraints = {
        name blank: true, nullable: true
        lastUpdated nullable: true
        songsNum min: 0, defaultValue: 0
    }
}