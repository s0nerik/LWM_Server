package lwm_server
import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class HistoryController {

    def lastFmService

    static responseFormats = ['json']
    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def testSongs = [
            [
                    artist   : "Asking Alexandria",
                    song     : "Dear Insanity",
                    cover_url: "http://www.mediaboom.org/uploads/posts/2011-04/1301988995_cover.jpg"
            ],
            [
                    artist   : "Asking Alexandria",
                    song     : "Don't Pray For Me",
                    cover_url: "http://ecx.images-amazon.com/images/I/51z9%2BHwOAML.jpg"
            ],
            [
                    artist   : "As I Lay Dying",
                    song     : "Cauterize",
                    cover_url: "http://upload.wikimedia.org/wikipedia/en/c/cc/Aild_awakened-cover.jpg"
            ],
            [
                    artist   : "As I Lay Dying",
                    song     : "Paralyzed",
                    cover_url: "http://www.mediaboom.org/uploads/posts/2011-11/1320238877_artworks-000011340636-5w2l7s-original.jpg"
            ],
            [
                    artist   : "As I Lay Dying",
                    song     : "Beyond Our Suffering",
                    cover_url: "http://upload.wikimedia.org/wikipedia/en/0/02/As_I_Lay_Dying_-_Powerless_Rise.jpg"
            ]
    ] * 50

    def show() {
        Collections.shuffle(testSongs)
        def hist = testSongs.toArray() as JSON


//        lastFmService.updateSong(null)


        respond hist, [status: OK]
    }

}
