package lwm_server

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class SettingsController {

    def tokenStorageService
    def userDetailsService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [show: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Settings.list(params), [status: OK]
    }

    def show() {
        respond request.user.settings, [status: OK]
    }

    @Transactional
    def save(Settings settingsInstance) {
        if (settingsInstance == null) {
            render status: NOT_FOUND
            return
        }

        settingsInstance.user = request.user
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
