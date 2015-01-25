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

    void "test validating PlaybackHistory without a Date and User"() {
        given:
        PlaybackHistory p = new PlaybackHistory()

        expect:
        p.validate() == false
    }

    void "test validating PlaybackHistory without a Date, but with User"() {
        given:
        PlaybackHistory p = new PlaybackHistory(user: new User())

        expect:
        p.validate() == false
    }

    void "test validating PlaybackHistory with a Date, but without User"() {
        given:
        PlaybackHistory p = new PlaybackHistory(date: new Date(System.currentTimeMillis()))

        expect:
        p.validate() == false
    }

    void "test saving PlaybackHistory without User"() {
        given:
        PlaybackHistory p = new PlaybackHistory(date: new Date(System.currentTimeMillis()))

        when:
        p.save()

        then:
        p.hasErrors() == true

        cleanup:
        p.delete()
    }

    void "test validating PlaybackHistory with User"() {
        given:
        User user = new User()
        PlaybackHistory p = new PlaybackHistory(date: new Date(System.currentTimeMillis()), user: user)

        expect:
        p.validate() == true
    }

}
