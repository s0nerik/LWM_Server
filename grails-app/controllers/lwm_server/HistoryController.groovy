package lwm_server

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = false)
class HistoryController {

    def lastFmService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        respond(request.user.songs.toArray() as JSON, [status: OK])
    }

    def save(Song song) {
        lastFmService.updateSong(song)

        def historyItem = new PlaybackHistoryItem(date: new Date(System.currentTimeMillis()), song: song)
        historyItem.save()

        request.user.addToPlaybackHistory(historyItem)
        request.user.save()
        respond(song as JSON, [status: OK])
    }

}
