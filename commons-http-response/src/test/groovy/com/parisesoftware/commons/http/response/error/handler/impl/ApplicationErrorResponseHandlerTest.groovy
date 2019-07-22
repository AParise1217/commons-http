package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.error.impl.InternalServerErrorResponse
import spock.lang.Specification

class ApplicationErrorResponseHandlerTest extends Specification {

    def "getNotificationTypesHandled(): should return the correct collection of Notification Type"() {

        when: 'a new ApplicationErrorResponseHandler is created and the notification types handled is queried for'
        List resultant = new ApplicationErrorResponseHandler(null).getNotificationTypesHandled()

        then: 'the resultant collection has one entry'
        resultant.size() == 1

        and: 'the collection contains `NotificationType.APPLICATION_ERROR`'
        resultant.contains(NotificationType.APPLICATION_ERROR)
    }

    def "getResponse(): should delegate to InternalServerErrorResponse.from()"() {

        given: 'a sample Data Transfer Object'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a global InternalServerErrorResponse Mock'
        GroovyMock(InternalServerErrorResponse, global: true)

        when: 'a new ApplicationErrorResponseHandler is created and a Response is generated from our sample DTO'
        new ApplicationErrorResponseHandler(null).getResponse(testDTO)

        then: 'InternalServerErrorResponse.from() was invoked with our sample DTO'
        1 * InternalServerErrorResponse.from(testDTO)
    }

}
