package com.parisesoftware.commons.http.notification

import spock.lang.Specification

class NotificationTest extends Specification {

    Notification notification = new Notification()

    def 'constructor: should initialize an empty errors list'() {

        expect: 'the Notification should not be null'
        notification != null

        and: 'the errors list is not null, and empty'
        with(notification.errors) {
            it != null
            size() == 0
        }
    }

    def 'hasErrors(): should return false when there are zero errors'() {

        when: 'the notification, with no errors, is interrogated for if it has errors'
        boolean resultant = notification.hasErrors()

        then: 'the resultant is false'
        !resultant
    }

    def 'hasErrorsFor(): should return false when there are no errors'() {

        when: 'the notification, with no errors, is interrogated for if it has errors'
        boolean resultant = notification.hasErrorsFor(NotificationType.APPLICATION_ERROR)

        then: 'the resultant is false'
        !resultant
    }

    def 'hasErrors(): should return true when there is one error'() {
        given: 'a new NotificationError is inserted into the errors list'
        notification.errors.add(new NotificationError(message: '... Don\'t try it Anakin. I have the high ground!'))

        when: 'the notification, with one error, is interrogated for if it has errors'
        boolean resultant = notification.hasErrors()

        then: 'the resultant is true'
        resultant
    }

    def 'hasErrors(): should return true when there is more than one error'() {
        given: 'an additional new NotificationError is inserted into the errors list'
        notification.errors.add(new NotificationError(message: 'How wude!'))

        when: 'the notification, with more than one error, is interrogated for if it has errors'
        boolean resultant = notification.hasErrors()

        then: 'the resultant is true'
        resultant
    }

    def 'hasErrorsFor(): should return true when there is an error of the given type'() {
        given: 'an additional new NotificationError is inserted into the errors list'
        notification.errors.add(new NotificationError(message: 'I\'ve been wondering... what are midi-chlorians?', type: NotificationType.APPLICATION_ERROR))

        when: 'the notification is interrogated for if it has an error of the given type'
        boolean resultant = notification.hasErrorsFor(NotificationType.APPLICATION_ERROR)

        then: 'the resultant is true'
        resultant
    }

    def 'hasErrorsFor(): should return false when there is not error of the given type'() {
        given: 'an additional new NotificationError is inserted into the errors list'
        notification.errors.add(new NotificationError(message: 'I don\'t like sand. It\'s coarse and rough and irritating and it gets everywhere', type: NotificationType.MISSING_PATH_PARAMETER))

        when: 'the notification is interrogated for if it has any errors of a different type'
        boolean resultant = notification.hasErrorsFor(NotificationType.MISSING_BODY_PARAMETER)

        then: 'the resultant is false'
        !resultant
    }

    def 'hasError(): should return true when the given error is not present'() {

        given: 'a sample NotificationError'
        NotificationError testError = new NotificationError(message: 'Now THIS is pod racing', type: NotificationType.APPLICATION_ERROR)

        and: 'the sample NotificationError is added to the errors list'
        notification.errors.add(testError)

        when: 'the notification is interrogated for if it has the error'
        boolean resultant = notification.hasError(testError)

        then: 'the resultant is true'
        resultant
    }

    def 'hasError(): should return false when the given error is not present'() {

        when: 'the notification is interrogated for if it the error'
        boolean resultant = notification.hasError(new NotificationError(type: NotificationType.MISSING_PATH_PARAMETER, message: 'something that has never been added'))

        then: 'the resultant is false'
        !resultant
    }

    def 'replaceErrorIfPresent(): should not modify Error List when the given error is not present'() {

        given: 'a List Spy is created as a Partial Mock'
        List stubbedList = Spy(ArrayList)

        and: 'the notification has our stubbed List set as the error list'
        notification.errors = stubbedList

        when: 'the notification is queried to replace an error'
        notification.replaceErrorIfPresent(null, null)

        then: 'the Stubbed List\'s add and remove methods were never invoked'
        0 * stubbedList.remove(_ as NotificationError)
        0 * stubbedList.add(_ as NotificationError)
    }

    def 'replaceErrorIfPresent(): should remove the Existing Error and add the Replacement Error the given error is not present'() {

        given: 'a List Spy is created as a Partial Mock'
        List stubbedList = Spy(ArrayList)

        and: 'the stubbed list has a Sample Error added'
        NotificationError sampleError1 = new NotificationError(message: 'Oh, hi Mark')
        stubbedList.add(sampleError1)

        and: 'another Sample Error is created to replace the initial one'
        NotificationError sampleError2 = new NotificationError(message: 'tearing me apart!')

        and: 'the notification has our stubbed List set as the error list'
        notification.errors = stubbedList

        when: 'the notification is queried to replace an error'
        notification.replaceErrorIfPresent(sampleError1, sampleError2)

        then: 'the Stubbed List\'s add and remove methods were never invoked'
        1 * stubbedList.remove(sampleError1)
        1 * stubbedList.add(sampleError2)
    }

}
