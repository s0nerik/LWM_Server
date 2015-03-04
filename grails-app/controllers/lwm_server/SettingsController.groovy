package lwm_server

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class SettingsController {

    def tokenStorageService
    def userDetailsService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Settings.list(params), [status: OK]
    }

    @Transactional
    def save(Settings settingsInstance) {
        if (settingsInstance == null) {
            render status: NOT_FOUND
            return
        }

        def authToken = request.getHeader("Authorization").split(" ")[1]
        def u = tokenStorageService.loadUserByToken(authToken)
        def u1 = User.findByUsername u.username

        settingsInstance.user = u1
        settingsInstance.validate()
        if (settingsInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        settingsInstance.save flush: true
        respond settingsInstance, [status: CREATED]
    }

    @Transactional
    def update(Settings settingsInstance) {
        if (settingsInstance == null) {
            render status: NOT_FOUND
            return
        }

        settingsInstance.validate()
        if (settingsInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        settingsInstance.save flush: true
        respond settingsInstance, [status: OK]
    }

    @Transactional
    def delete(Settings settingsInstance) {

        if (settingsInstance == null) {
            render status: NOT_FOUND
            return
        }

        settingsInstance.delete flush: true
        render status: NO_CONTENT
    }
}
