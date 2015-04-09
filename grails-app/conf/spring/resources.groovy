import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import lwm_server.*
// Place your Spring DSL code here
beans = {
    oauthUserDetailsService(LWMOauthUserDetailsService) {
        userDetailsService = ref('userDetailsService')
    }

    /* JSON marshalling settings */

    def marshallingExcludes = [
            (User): [
                    'username',
                    'accountExpired',
                    'accountLocked',
                    'passwordExpired',
                    'password',
                    'enabled',
                    'favorites',
                    'playbackHistory',
                    'playlists',
                    'songs',
            ],
            (Settings): ['id'],
            (Song): [],
            (Artist): [],
            (Album): [],
            (Playlist): [],
            (PlaybackHistoryItem): ['user']
    ]

    for (def entry : marshallingExcludes) {
        def finalExcludes = ['class', 'errors', 'version'] + entry.value
        "${entry.key.name.toLowerCase()}CollectionRenderer"(JsonCollectionRenderer, entry.key) { excludes = finalExcludes }
        "${entry.key.name.toLowerCase()}Renderer"(JsonRenderer, entry.key) { excludes = finalExcludes }
    }
}