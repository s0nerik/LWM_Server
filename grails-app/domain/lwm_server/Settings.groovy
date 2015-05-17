package lwm_server

class Settings {

    String stationName

    static belongsTo = [user: User]

    static constraints = {
        stationName empty: true, nullable: true
    }

    static mapping = {
        table 'settings'
        stationName defaultValue: "''"
    }
}