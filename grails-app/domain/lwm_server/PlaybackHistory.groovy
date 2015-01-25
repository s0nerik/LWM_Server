package lwm_server

class PlaybackHistory {

    Date date

    static hasMany = [songs: Song]
    static belongsTo = [user: User]

    static constraints = {
        date nullable: false
    }

    static mapping = {
        table 'playback_histories'
    }
}
