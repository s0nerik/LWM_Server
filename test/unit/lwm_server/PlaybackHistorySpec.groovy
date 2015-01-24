package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PlaybackHistory)
class PlaybackHistorySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validating PlaybackHistory without a Date"() {
        given:
        PlaybackHistory p = new PlaybackHistory()

        expect:
        p.validate() == false
    }

    void "test validating PlaybackHistory with a Date"() {
        given:
        PlaybackHistory p = new PlaybackHistory(date: new Date(System.currentTimeMillis()))

        expect:
        p.validate() == true
    }


}
