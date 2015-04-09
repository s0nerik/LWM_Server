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

//        // Fake song ---
//        def artist = new Artist(name: "Asking Alexandria")
//        def album = new Album(title: "From Death To Destiny", year: 2009, artist: artist)
//        def s = new Song(title: "The Death Of Me", artist: artist, album: album)
//
//        if (artist.save(true)) {
//            if (album.save(true)) {
//                artist.addToAlbums(album)
//                if (s.save(true)) {
//                    album.addToSongs(s)
//                }
//            }
//        }
//        // Fake song ---

        if (!u.save(validate: true, flush: true)) {
            log.error("Can't save user, errors:\n${u.errors}")
        } else {

//            // Fake playlist ---
//            def playlist = new Playlist(created: new Date(1337), name: "playlist")
//            playlist.save()
//            playlist.addToSongs(s)
//            // Fake playlist ---
//
//            // Save fake data ---
//            u.addToPlaylists(playlist)
//            u.addToSongs(s)
//            u.addToFavorites(s)
//
//            u.save()
//            // Save fake data ---

            if (!UserRole.create(u, Role.findByAuthority("ROLE_USER"), true)) {
                log.error("Can't save user role.")
            }
        }

        return oauthUser
    }

}