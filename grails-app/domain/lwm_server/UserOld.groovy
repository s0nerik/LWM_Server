package lwm_server

class UserOld {

    String name
    String password
    String avatar_url

    static hasMany = [songs: Song, playbackHistory: PlaybackHistoryItem]

    static constraints = {
        name size: 2..50, blank: true, nullable: true
        password size: 8..20, blank: false
        avatar_url nullable: true
    }

}
