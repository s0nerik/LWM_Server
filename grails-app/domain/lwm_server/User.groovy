package lwm_server

class User {

    transient springSecurityService

    String name
    String avatar_url
    String profile_url
    String email

    String username
    String password

    Settings settings

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static embedded = ['settings']

    static hasMany = [songs: Song, favorites: Song, playbackHistory: PlaybackHistoryItem]

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true  // Google ID
        password blank: true, nullable: false

        email blank: false, unique: true
        name size: 2..50, blank: true, nullable: true
        avatar_url nullable: true
        profile_url nullable: true
        settings nullable: true
    }

    static mapping = {
        table 'users'
        password column: '`password`'

        songs joinTable: [name: 'users_songs']
        favorites joinTable: [name: 'users_favorites']
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}

class Settings {

    String stationName

    static constraints = {
        stationName nullable: true
    }

    static mapping = {
        table 'settings'
        stationName defaultValue: ""
    }
}