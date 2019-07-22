package com.parisesoftware.commons.http.response.error.handler

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.Notification
import com.parisesoftware.commons.http.notification.NotificationError
import com.parisesoftware.commons.http.notification.NotificationType
import spock.lang.Specification
import spock.lang.Unroll

class AbstractErrorResponseHandlerTest extends Specification {

    def "handle(): should invoke getResponse() when isApplicableHandler() returns true"() {

        given: 'a sample DTO'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a mocked NextHandler'
        AbstractErrorResponseHandler mockNextHandler = Mock(AbstractErrorResponseHandler)

        and: 'an AbstractErrorResponseHandler with stubbed methods; \
                to return true when isApplicableHandler() is invoked'

        boolean testIsApplicableHandler = true

        AbstractErrorResponseHandler testResponseHandler = Spy(constructorArgs:[mockNextHandler]) {
            1 * isApplicableHandler(testDTO) >> testIsApplicableHandler
            1 * getResponse(testDTO) >> null
        }

        when: 'execute() is invoked on our stubbed AbstractErrorResponseHandler'
        testResponseHandler.handle(testDTO)

        then: 'our mocked NextHandler\'s execute() method is never invoked'
        0 * mockNextHandler.handle(_ as DataTransferObject)
    }

    def "handle(): should invoke the nextHandler\'s handle() method when isApplicableHandler() returns false"() {

        given: 'a sample DTO'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a mocked NextHandler'
        AbstractErrorResponseHandler mockNextHandler = Mock(AbstractErrorResponseHandler)

        and: 'an AbstractErrorResponseHandler with stubbed methods; \
                to return false when isApplicableHandler() is invoked'

        boolean testIsApplicableHandler = false

        AbstractErrorResponseHandler testResponseHandler = Spy(constructorArgs:[mockNextHandler]) {
            1 * isApplicableHandler(testDTO) >> testIsApplicableHandler
            0 * getResponse(_ as DataTransferObject) >> null
        }

        when: 'execute() is invoked on our stubbed AbstractErrorResponseHandler'
        testResponseHandler.handle(testDTO)

        then: 'our mocked NextHandler\'s execute() method is invoked with our sample DTO'
        1 * mockNextHandler.handle(testDTO)
    }

    def "handle(): should not throw any exceptions when the nextHandler is null and isApplicableHandler() returns false"() {

        given: 'a sample DTO'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a null NextHandler'
        AbstractErrorResponseHandler mockNextHandler = null

        and: 'an AbstractErrorResponseHandler with stubbed methods; \
                to return false when isApplicableHandler() is invoked'

        boolean testIsApplicableHandler = false

        AbstractErrorResponseHandler testResponseHandler = Spy(constructorArgs:[mockNextHandler]) {
            1 * isApplicableHandler(testDTO) >> testIsApplicableHandler
            0 * getResponse(_ as DataTransferObject) >> null
        }

        when: 'execute() is invoked on our stubbed AbstractErrorResponseHandler'
        testResponseHandler.handle(testDTO)

        then: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "isDefaultHandler(): should return false"() {

        // Note: this must be a spy, since this test is for a method implementation on an abstract class
        given: 'a Response Handler Spy'
        AbstractErrorResponseHandler testResponseHandler = Spy()

        when: 'isDefaultHandler() is invoked on our sample Response Handler'
        def resultant = testResponseHandler.isDefaultHandler()

        then: 'the resultant boolean is false'
        !resultant
    }

    @Unroll
    def "isApplicableHandler(): should return `#expectedValue`, \
            when isDefaultHandler() >> `#testIsDefaultHandler`, \
            and isHandlerFor() >> `#testIsHandlerFor`"(boolean testIsDefaultHandler, boolean testIsHandlerFor, boolean expectedValue) {

        given: 'a sample DTO'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'an AbstractErrorResponseHandler with stubbed methods'

        AbstractErrorResponseHandler testResponseHandler = Spy() {
            isDefaultHandler() >> testIsDefaultHandler
            isHandlerFor(testDTO) >> testIsHandlerFor
        }

        expect: 'the resultant of isApplicableHandler()  to equal `#expectedValue`'
        boolean resultant = testResponseHandler.isApplicableHandler(testDTO)
        resultant == expectedValue

        where:
        testIsDefaultHandler    | testIsHandlerFor  | expectedValue
        true                    | true              | true
        true                    | false             | true
        false                   | true              | true
        false                   | false             | false
    }

    @Unroll
    def "isHandlerFor(): should return `#expectedValue`, \
            when the DTO Notification Contains `#testErrors`, \
            and the Handler Handles `#testHandledTypes`"(List<NotificationError> testErrors, List<NotificationType> testHandledTypes, boolean expectedValue) {

        given: 'a sample DTO containing our error list'
        DataTransferObject testDTO = new DataTransferObject(notification: new Notification(errors: testErrors))

        and: 'an AbstractErrorResponseHandler with stubbed methods'
        AbstractErrorResponseHandler testResponseHandler = Spy() {
            getNotificationTypesHandled() >> testHandledTypes
        }

        expect: 'the resultant of isHandlerFor()  to equal `#expectedValue`'
        boolean resultant = testResponseHandler.isHandlerFor(testDTO)
        resultant == expectedValue

        where:
        testErrors                                                             | testHandledTypes                          || expectedValue
        []                                                                     | []                                        || false
        []                                                                     | [NotificationType.INVALID_PATH_PARAMETER] || false
        [new NotificationError(type: NotificationType.APPLICATION_ERROR)]      | []                                        || false
        [new NotificationError(type: NotificationType.MISSING_PATH_PARAMETER)] | [NotificationType.MISSING_PATH_PARAMETER] || true
    }


}
