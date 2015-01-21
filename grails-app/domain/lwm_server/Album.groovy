package lwm_server

class Album {

    String title
    String coverUrl
    int year

    static hasMany = [songs: Song]
    static belongsTo = [artist: Artist]

    static constraints = {
    }

    static mapping = {
        table 'albums'
    }
}
