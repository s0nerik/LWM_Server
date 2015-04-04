import com.odobo.grails.plugin.springsecurity.rest.oauth.OauthUser
import com.odobo.grails.plugin.springsecurity.rest.oauth.OauthUserDetailsService
import lwm_server.*
import org.pac4j.core.profile.CommonProfile
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class LWMOauthUserDetailsService implements OauthUserDetailsService {

    @Delegate
    UserDetailsService userDetailsService

//    def testSongs = [
//            [
//                    artist   : "Asking Alexandria",
//                    song     : "Dear Insanity",
//                    cover_url: "http://www.mediaboom.org/uploads/posts/2011-04/1301988995_cover.jpg"
//            ],
//            [
//                    artist   : "Asking Alexandria",
//                    song     : "Don't Pray For Me",
//                    cover_url: "http://ecx.images-amazon.com/images/I/51z9%2BHwOAML.jpg"
//            ],
//            [
//                    artist   : "As I Lay Dying",
//                    song     : "Cauterize",
//                    cover_url: "http://upload.wikimedia.org/wikipedia/en/c/cc/Aild_awakened-cover.jpg"
//            ],
//            [
//                    artist   : "As I Lay Dying",
//                    song     : "Paralyzed",
//                    cover_url: "http://www.mediaboom.org/uploads/posts/2011-11/1320238877_artworks-000011340636-5w2l7s-original.jpg"
//            ],
//            [
//                    artist   : "As I Lay Dying",
//                    song     : "Beyond Our Suffering",
//                    cover_url: "http://upload.wikimedia.org/wikipedia/en/0/02/As_I_Lay_Dying_-_Powerless_Rise.jpg"
//            ]
//    ] * 50

    @Override
    OauthUser loadUserByUserProfile(CommonProfile userProfile, Collection<GrantedAuthority> defaultRoles) throws UsernameNotFoundException {
        UserDetails userDetails
        OauthUser oauthUser

        try {
            log.debug "Trying to fetch user details for user profile: ${userProfile}"
            userDetails = userDetailsService.loadUserByUsername userProfile.id
            Collection<GrantedAuthority> allRoles = userDetails.authorities + defaultRoles
            oauthUser = new OauthUser(userDetails.username, userDetails.password, allRoles, userProfile)
        } catch (UsernameNotFoundException unfe) {
            log.debug "User not found. Creating a new one with default roles: ${defaultRoles}"
            oauthUser = new OauthUser(userProfile.id, 'N/A', defaultRoles, userProfile)
        }

        def u = User.findByUsername(userProfile.id)

        if (!u) {
            u = new User(
                    username: userProfile.id,
                    password: 'N/A',
                    email: userProfile.attributes.email,
                    name: userProfile.attributes.name,
                    profile_url: userProfile.attributes.link,
                    avatar_url: userProfile.attributes.picture
            )
        }

        def artist = new Artist(name: "Asking Alexandria")
        def album = new Album(artist: artist, title: "From Death To Destiny")
        def s = new Song(title: "The Death Of Me", artist: artist, album: album)

        if (artist.save(true)) {
            if (album.save(true)) {
                if (s.save(true)) {
                    def x = 3 + 8*5;
                }
            }
        }

        if (!u.save(validate: true, flush: true)) {
            log.error("Can't save user, errors:\n${u.errors}")
        } else {
            u.addToSongs(s)

            u.save(flush: true)

            if (!UserRole.create(u, Role.findByAuthority("ROLE_USER"), true)) {
                log.error("Can't save user role.")
            }
        }

        return oauthUser
    }

}