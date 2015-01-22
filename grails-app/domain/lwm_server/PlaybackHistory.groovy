package lwm_server

class PlaybackHistory {

    Date date

    static hasMany = [songs: Song]
    static belongsTo = User

    static constraints = {
    }

    static mapping = {
        table 'playback_histories'
    }
}
