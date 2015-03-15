package lwm_server

class User {

    transient springSecurityService

    String name
    String avatar_url
    String profile_url
    String email

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [songs: Song, playbackHistory: PlaybackHistoryItem]
    static hasOne = [settings: Settings]

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
