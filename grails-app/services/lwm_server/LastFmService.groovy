package lwm_server

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional(readOnly = false)
class LastFmService {

    def grailsApplication

    def ENDPOINT = "http://ws.audioscrobbler.com/2.0/"

    def updateSong(Song song) {
//        def resp = rest.get(ENDPOINT, [api_key: KEY, track: song.title, artist: song.album.artist])
        def form = [
                method: "track.getInfo",
                api_key: grailsApplication.config.lastfm.key,
                track: song.title,
                artist: song.artist.name,
                format: "json",
                autocorrect: 1
        ]

        def queryString = form.collect { k, v -> "$k=$v" }.join(/&/)

        def resp = new RestBuilder().get("$ENDPOINT?$queryString") {
            header 'Accept', 'application/json'
            contentType "application/x-www-form-urlencoded"
        }

        def track = resp?.json?.track

        def album = song.album
        if (!song.album) {
            def artist = song.artist
            artist.save()
            album = new Album(artist: artist, title: "unknown")
        }

        album.coverUrl = track?.album?.image?.getAt(-1)?."#text"
        album.title = track?.album?.title ?: album.title

        album.save()

        song.album = album

        song.duration = song?.duration ?: track?.duration as int

        song.save()
    }
}
