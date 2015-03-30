import com.odobo.grails.plugin.springsecurity.rest.oauth.OauthUser
import com.odobo.grails.plugin.springsecurity.rest.oauth.OauthUserDetailsService
import lwm_server.Role
import lwm_server.User
import lwm_server.UserRole
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

        if (!u.save(true)) {
            log.error("Can't save user, errors:\n${u.errors}")
        } else {
            if (!UserRole.create(u, Role.findByAuthority("ROLE_USER"), true)) {
                log.error("Can't save user role.")
            }
        }

        return oauthUser
    }

}