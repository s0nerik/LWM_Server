package lwm_server

class Settings {

    String stationName;

    static constraints = {
        stationName nullable: true
    }

    static belongsTo = [User]
}
