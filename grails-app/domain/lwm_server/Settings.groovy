package lwm_server

class Settings {

    String stationName
    boolean alwaysShuffle

    static belongsTo = [user: User]

    static constraints = {
        stationName empty: true, nullable: true
    }

    static mapping = {
        table 'settings'
        stationName defaultValue: "''"
        alwaysShuffle defaultValue: "false"
    }
}