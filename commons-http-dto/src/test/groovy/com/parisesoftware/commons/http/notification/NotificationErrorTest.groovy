package com.parisesoftware.commons.http.notification

import spock.lang.Specification

class NotificationErrorTest extends Specification {

    NotificationError notificationError = new NotificationError()

    def 'type: should default to APPLICATION_ERROR'() {

        expect: 'a new NotificationError is instantiated with no values provided'
        notificationError.type == NotificationType.APPLICATION_ERROR
    }

    def 'getMessage/setMessage: should get/set the message properly'() {

        given: 'a sample message'
        String testMessage = 'Uh! So uncivilized.'

        when: 'the NotificationError message is set to our sample message'
        notificationError.message = testMessage

        then: 'the message is equivalent to our sample message'
        notificationError.message == testMessage
    }

    def 'getType/setType: should get/set the type properly'() {

        given: 'a sample type'
        NotificationType testType = NotificationType.INVALID_BODY_PARAMETER

        when: 'the NotificationError type is set to our sample type'
        notificationError.type = testType

        then: 'the type is equivalent to our sample type'
        notificationError.type == testType
    }

}
