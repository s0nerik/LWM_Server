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

    def "create a new User"() {
        given:
        def u = new User(email: "sonerik@mail.ua", password: "12345678")

        when:
        u.save()

        then:
        User.findAll {it.email == "sonerik@mail.ua"}.size() == 1

        cleanup:
        u.delete()
    }

    def "can't create a new User with broken email"() {
        given:
        def u = new User(email: "tatae_4dc", password: "12345678")

        when:
        u.save()

        then:
        User.findAll {it.email == "tatae_4dc"}.size() == 0

        cleanup:
        u.delete()
    }

    def "validatation of a User with broken email should fail"() {
        given:
        def u = new User(email: "tatae_4dc", password: "12345678")

        expect:
        u.validate() == false
    }

    def "validatation of a User with too short password should fail"() {
        given:
        def u = new User(email: "sonerik@mail.ua", password: "123456")

        expect:
        u.validate() == false
    }

    def "can't create two or more Users with same email"() {
        given:
        def u1 = new User(name: "user1", email: "sonerik@mail.ua", password: "12345678")
        def u2 = new User(name: "user2", email: "sonerik@mail.ua", password: "123456789")

        when:
        u1.save(flush: true, validate: true)
        u2.save(flush: true, validate: true)

        then:
        User.findAll { it.email == "sonerik@mail.ua" }.size() == 1

        cleanup:
        u1.delete()
        u2.delete()
    }

}
