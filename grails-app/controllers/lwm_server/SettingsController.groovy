package lwm_server

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.OK

@Secured(["ROLE_USER"])
@Transactional(readOnly = false)
class SettingsController {

    static responseFormats = ['json']
    static allowedMethods = [show: "GET", update: "PUT"]

    def show() {
        def settings = Settings.findOrSaveByUser(request.user)
        respond([id    : settings.id,
                 groups: [
                                 [
                                         title   : 'Station settings',
                                         settings: [
                                                 [
                                                         title: 'Station name',
                                                         attr : 'stationName',
                                                         value: settings.stationName ?: ""
                                                 ]
                                         ]
                                 ],
                                 [
                                         title   : 'Playback settings',
                                         settings: [
                                                 [
                                                         title: 'Always play shuffled',
                                                         attr : 'alwaysShuffle',
                                                         value: settings.alwaysShuffle
                                                 ]
                                         ]
                                 ]
                         ]
        ] as JSON, [status: OK])
    }

    def update(Settings settings) {
        request.user.settings = settings
        request.user.save()

        respond request.user.settings as JSON, [status: OK]
    }

}
