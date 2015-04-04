package lwm_server

class Album {

    String title
    String coverUrl
    int year

    static hasMany = [songs: Song]
    static belongsTo = [artist: Artist]

    static constraints = {
        title blank: true, nullable: true
        coverUrl blank: true, nullable: true
        year blank: true, nullable: true
    }

    static mapping = {
        table 'albums'
    }
}
