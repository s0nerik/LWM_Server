package lwm_server

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "create new User"() {
        given:
        def u = new User(email: "sonerik@mail.ua", password: "12345678")

        when:
        u.save()

        then:
        User.findAll {it.email == "sonerik@mail.ua"}.size() == 1

        cleanup:
        u.delete()
    }

}
