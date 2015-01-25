package lwm_server

class PlaybackHistoryItem {

    Date date

    static hasOne = Song
    static belongsTo = [user: User]

    static constraints = {
        date nullable: false
    }

    static mapping = {
        table 'playback_histories'
    }
}
