package lwm_server

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class LastFmService {

    def grailsApplication

    def ENDPOINT = "http://ws.audioscrobbler.com/2.0/"

    def updateSong(Song song) {
//        def resp = rest.get(ENDPOINT, [api_key: KEY, track: song.title, artist: song.album.artist])
        def form = [
                method: "track.getInfo",
                api_key: grailsApplication.config.lastfm.key,
                track: song.title,
                artist: song.album.artist,
                format: "json",
                autocorrect: 1
        ]

        def queryString = form.collect { k, v -> "$k=$v" }.join(/&/)

        def resp = new RestBuilder().get("$ENDPOINT?$queryString") {
            header 'Accept', 'application/json'
            contentType "application/x-www-form-urlencoded"
        }

        song.album.coverUrl = resp?.json?.track?.album?.image[-1]?."#text"

        song.save()

//        def resp = rest.get(ENDPOINT, [format: "json", method: "track.getInfo", api_key: grailsApplication.config.lastfm.key, track: "Don't pray for me", artist: "Asking Alexandria"])
        log.error(resp.json)
    }
}
