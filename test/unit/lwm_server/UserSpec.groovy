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

    def "test creating a new User"() {
        given:
        def u = new User(
                email: "sonerik@mail.ua",
                password: "12345678",
                googleID: "12345678",
                username: "Alex"
        )

        when:
        u.save()

        then:
        User.findAll {it.email == "sonerik@mail.ua"}.size() == 1

        cleanup:
        u.delete()
    }

    def "test creating a new User with broken email"() {
        given:
        def u = new User(email: "tatae_4dc", password: "12345678")

        when:
        u.save()

        then:
        User.findAll {it.email == "tatae_4dc"}.size() == 0

        cleanup:
        u.delete()
    }

    def "test validating a User with broken email"() {
        given:
        def u = new User(email: "tatae_4dc", password: "12345678")

        expect:
        u.validate() == false
    }

    def "test validating a User with too short password"() {
        given:
        def u = new User(email: "sonerik@mail.ua", password: "123456")

        expect:
        u.validate() == false
    }

    def "test creating two or more Users with same email"() {
        given:
        def u1 = new User(name: "user1", googleID: "12345678", username: "Alex", email: "sonerik@mail.ua", password: "12345678")
        def u2 = new User(name: "user2", googleID: "65436543", username: "Terrance", email: "sonerik@mail.ua", password: "123456789")

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
